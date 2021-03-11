package com.springau.utils;

import java.io.ByteArrayInputStream;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public class InputClass {
  private ByteArrayInputStream input;

  public ByteArrayInputStream getInput() {
    return input;
  }

  public void setInput(ByteArrayInputStream input) {
    this.input = input;
  }

  public InputClass(ByteArrayInputStream input) {
    super();
    this.input = input;
  }

}
