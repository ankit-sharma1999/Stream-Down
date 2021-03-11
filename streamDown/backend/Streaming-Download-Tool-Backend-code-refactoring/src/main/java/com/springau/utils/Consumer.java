package com.springau.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springau.producers.StudentProducer;

@Component
public class Consumer implements Runnable {

  @Autowired
  ExcelConverter excelConverter;

  @Autowired
  QueueContainer queueContainer;

  InputClass inputClass;
  
  private static Logger logger = LoggerFactory.getLogger(StudentProducer.class);

  public void setInputClass(InputClass inputClass) {
    this.inputClass = inputClass;
  }

  @Override
  public void run() {
    try {
      while (true) {
        List<List<String>> queueData = queueContainer.getQueue()
            .take();
        logger.info("From consumer" + queueData);
        excelConverter.addRows(queueData, inputClass);
        if (queueData.isEmpty()) {
          break;
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
