package com.springau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.springau.utils.InputClass;

@SpringBootApplication
public class StreamDownApplication {

  public static void main(String[] args) {
    SpringApplication.run(StreamDownApplication.class, args);
  }

  @Bean
  public InputClass inputClass() {
    return null;
  }

}
