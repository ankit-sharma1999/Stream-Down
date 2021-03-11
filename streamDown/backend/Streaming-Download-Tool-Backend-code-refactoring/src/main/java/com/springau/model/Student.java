package com.springau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {



  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", roll=" + roll + ", department=" + department
        + ", courseId=" + courseId + ", assignmentId=" + assignmentId + ", personalDetails="
        + personalDetails + "]";
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String name;

  @Column
  private int roll;

  @Column
  private String department;

  @Column
  private int courseId;

  @Column
  private int assignmentId;

  @Column
  private String personalDetails;

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(int assignmentId) {
    this.assignmentId = assignmentId;
  }

  public String getPersonalDetails() {
    return personalDetails;
  }

  public void setPersonalDetails(String personalDetails) {
    this.personalDetails = personalDetails;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRoll() {
    return roll;
  }

  public void setRoll(int roll) {
    this.roll = roll;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Student() {

  }

  public Student(String name, int roll, String department, int courseId, int assignmentId,
      String personalDetails) {
    super();
    this.name = name;
    this.roll = roll;
    this.department = department;
    this.courseId = courseId;
    this.assignmentId = assignmentId;
    this.personalDetails = personalDetails;
  }



}
