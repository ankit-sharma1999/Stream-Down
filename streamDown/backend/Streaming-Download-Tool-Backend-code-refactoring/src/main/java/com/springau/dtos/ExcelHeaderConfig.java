package com.springau.dtos;

import java.util.List;

public class ExcelHeaderConfig {

  private List<String> headers;
  private String sheetName;

  public List<String> getHeaders() {
    return headers;
  }

  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }

  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public ExcelHeaderConfig(List<String> headers, String sheetName) {
    super();
    this.headers = headers;
    this.sheetName = sheetName;
  }
}
