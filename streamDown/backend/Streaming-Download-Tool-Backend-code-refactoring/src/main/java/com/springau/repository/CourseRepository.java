package com.springau.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.annotation.RequestScope;
import com.springau.model.Course;

@RequestScope
public interface CourseRepository extends JpaRepository<Course, Integer> {
  Page<Course> findAll(Pageable pageable);

}
