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
import com.springau.model.Course;
import com.springau.repository.CourseRepository;
import com.springau.utils.QueueContainer;

@Component
public class CourseProducer implements Runnable {

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  QueueContainer queueContainer;

  @Value("${db.page.req.max.size}")
  private Integer pageSize;
  
  private static Logger logger = LoggerFactory.getLogger(CourseProducer.class);

  @Override
  public void run() {
    int pageNum = 0;
    while (true) {
      Pageable pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("id"));
      Page<Course> pageData = courseRepository.findAll(pageRequest);
      List<List<String>> courses = convertToString(pageData.getContent());
      queueContainer.getQueue()
          .add(courses);
      logger.info("From Producer"+courses);
      if (courses.isEmpty()) {
        break;
      }
      pageNum += 1;
    }
  }

  private List<List<String>> convertToString(List<Course> courseData) {
    return courseData.stream()
        .map(course -> convertCourseToString(course))
        .collect(Collectors.toList());
  }

  private List<String> convertCourseToString(Course course) {
    List<String> toStringArray = new ArrayList<>();
    toStringArray.add(String.valueOf(course.getId()));
    toStringArray.add(course.getName());
    toStringArray.add(String.valueOf(course.getFee()));
    toStringArray.add(course.getProfessor());
    
    return toStringArray;
  }

}
