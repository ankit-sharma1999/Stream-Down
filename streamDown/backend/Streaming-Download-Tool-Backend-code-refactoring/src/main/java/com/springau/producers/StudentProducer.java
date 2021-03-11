package com.springau.producers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.springau.model.Student;
import com.springau.repository.StudentRepository;
import com.springau.utils.QueueContainer;

@Component
public class StudentProducer implements Runnable {

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  QueueContainer queueContainer;

  @Value("${db.page.req.max.size}")
  private Integer pageSize;
  
  private static Logger logger = LoggerFactory.getLogger(StudentProducer.class);

  @Override
  public void run() {
    int pageNum = 0;
    while (true) {
      Pageable pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("id"));
      Page<Student> pageData = studentRepository.findAll(pageRequest);
      List<List<String>> students = convertToString(pageData.getContent());
      queueContainer.getQueue()
          .add(students);
      logger.info("From Producer"+students);
      if (students.isEmpty()) {
        break;
      }
      pageNum += 1;
    }
  }

  private List<List<String>> convertToString(List<Student> studentData) {
    return studentData.stream()
        .map(student -> convertStudentToString(student))
        .collect(Collectors.toList());
  }

  private List<String> convertStudentToString(Student student) {
    List<String> toStringArray = new ArrayList<>();
    toStringArray.add(String.valueOf(student.getId()));
    toStringArray.add(student.getName());
    toStringArray.add(String.valueOf(student.getRoll()));
    toStringArray.add(student.getDepartment());
    toStringArray.add(String.valueOf(student.getCourseId()));
    toStringArray.add(String.valueOf(student.getAssignmentId()));
    toStringArray.add(student.getPersonalDetails());
    return toStringArray;
  }

}
