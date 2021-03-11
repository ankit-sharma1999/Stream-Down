package com.springau.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import com.springau.enums.EntityResolutionEnum;
import com.springau.model.Course;
import com.springau.repository.CourseRepository;
import com.springau.repository.StudentRepository;
import com.springau.service.ConverterService;
import com.springau.service.impl.BeanFactoryService;
import com.springau.utils.ByteConverter;
import com.springau.utils.InputClass;

@RestController
@RequestMapping("FileDownloader")
public class FileController {

  @Autowired
  BeanFactoryService beanFactoryService;

  @Autowired
  StudentRepository studentrepo;
  
  @Autowired
  CourseRepository repo;

  private static Logger logger = LoggerFactory.getLogger(FileController.class);
  
  @GetMapping("/get/excel/{exportType}")
  @org.springframework.transaction.annotation.Transactional(timeout = 120)
  public ResponseEntity<StreamingResponseBody> getEntityFile(@PathVariable String exportType) {
    String fileName = exportType + ".xlsx";
    InputClass inputClass = new InputClass(null);

    Class<? extends ConverterService> convServiceName =
        EntityResolutionEnum.getServiceClassForType(exportType);
    ConverterService converterService = beanFactoryService.getBeanForClass(convServiceName);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(streamResponse(inputClass, converterService));
  }

  private StreamingResponseBody streamResponse(InputClass inputClass,
      ConverterService converterService) {
    return out -> {
      try {
        converterService.loadData(inputClass);
        out.write(ByteConverter.getData(inputClass));
      } catch (InterruptedException e) {
        logger.info(e.getMessage());
      }
    };
  }

  @GetMapping("/add/records")
  @Transactional
  public void addRecords() {
//    List<Student> studentList = new ArrayList<>();
//    for (int i = 0; i < 10000; i++) {
//      studentList.add(new Student("name" + i, i, "department" + i, i, i, "personal" + i));
//    }
//    repo.saveAll(studentList);
    
    List<Course> courseList = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      courseList.add(new Course("name" + i, i, "professor" + i, i));
    }
    repo.saveAll(courseList);
  }
}
