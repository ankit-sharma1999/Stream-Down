package com.springau.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import com.springau.dtos.ExcelHeaderConfig;
import com.springau.producers.CourseProducer;
import com.springau.service.ConverterService;
import com.springau.utils.Consumer;
import com.springau.utils.ExcelConverter;
import com.springau.utils.InputClass;
import com.springau.utils.QueueContainer;

@RequestScope
@Component
public class CourseConverterServiceImpl implements ConverterService {

  @Autowired
  CourseProducer producer;

  @Autowired
  Consumer consumer;

  @Autowired
  QueueContainer queueContainer;

  @Autowired
  ExcelConverter excelConverter;


  @Override
  public void loadData(InputClass inputClass) throws InterruptedException {

    List<String> courseHeaders = Arrays.asList("Id", "Name", "Fee", "Professor", "StudentID");
    ExcelHeaderConfig courseHeaderConfig = new ExcelHeaderConfig(courseHeaders, "Courses");
    excelConverter.createHeader(courseHeaderConfig);

    Thread producerThread = new Thread(producer);
    producerThread.start();

    consumer.setInputClass(inputClass);
    Thread consumerThread = new Thread(consumer);
    consumerThread.start();

    producerThread.join();
    consumerThread.join();
  }
}
