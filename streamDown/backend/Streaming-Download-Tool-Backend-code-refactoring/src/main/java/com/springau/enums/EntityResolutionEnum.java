package com.springau.enums;

import com.springau.service.ConverterService;
import com.springau.service.impl.StudentConverterServiceImpl;
import com.springau.service.impl.CourseConverterServiceImpl;

public enum EntityResolutionEnum {

  STUDENT("Student", StudentConverterServiceImpl.class),
  
  COURSE("Course", CourseConverterServiceImpl.class);

  private final String key;
  private final Class<? extends ConverterService> serviceClassName;

  private EntityResolutionEnum(String key, Class<? extends ConverterService> serviceClassName) {
    this.key = key;
    this.serviceClassName = serviceClassName;
  }

  public static Class<? extends ConverterService> getServiceClassForType(String entityType) {
    for (EntityResolutionEnum type : values()) {
      if (type.key.equals(entityType))
        return type.serviceClassName;
    }
    return null;
  }

}
