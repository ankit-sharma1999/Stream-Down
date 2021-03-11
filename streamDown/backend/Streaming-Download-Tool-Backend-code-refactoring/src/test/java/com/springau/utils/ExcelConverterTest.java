package com.springau.utils;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExcelConverterTest {

  private Workbook workbook;
  private ByteArrayOutputStream output;
  private Sheet sheet;
  private int rownum = 1;

  @Autowired
  InputClass inputClass;
  
  

  @BeforeEach
  public void init() {
    workbook = new SXSSFWorkbook();
    output = new ByteArrayOutputStream();
    sheet = workbook.createSheet("Student");
    Row headerRow = sheet.createRow(0);

    List<String> headers = Arrays.asList("head1", "head2", "head3");
    for (int col = 0; col < headers.size(); col++) {
      Cell cell = headerRow.createCell(col);
      cell.setCellValue(headers.get(col));
    }
  }

  public void addRows(List<List<String>> records, InputClass inputClass) {
    if (records.isEmpty()) {
      try {
        workbook.write(output);
        inputClass.setInput(new ByteArrayInputStream(output.toByteArray()));
        rownum = 1;
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      for (List<String> record : records) {
        Row row = sheet.createRow(rownum++);
        int colNum = 0;
        for (String cellValue : record) {
          row.createCell(colNum)
              .setCellValue(cellValue);
          colNum++;
        }
      }
    }
  }

  @Test
  void test1() {
    List<List<String>> records = new ArrayList<>();
    addRows(records, new InputClass(null));
    assertEquals(1, rownum);
  }

  @Test
  void test2() {
    List<String> s1 = Arrays.asList("jshg", "hssj", "njhs");
    List<String> s2 = Arrays.asList("mbgsh", "hjjhxjh", "jhxhjbhjx");
    List<List<String>> records = new ArrayList<>();
    records.add(s1);
    records.add(s2);
    addRows(records, new InputClass(null));
    assertEquals(3, rownum);
  }
  
  @Test
  void test3() {
    List<String> s1 = Arrays.asList("jshg", "hssj", "njhs");
    List<String> s2 = Arrays.asList("mbgsh", "hjjhxjh", "jhxhjbhjx");
    List<String> s3 = Arrays.asList("mhdhv", "hsjdaloh", "jhkjshsx");
    List<List<String>> records = new ArrayList<>();
    records.add(s1);
    records.add(s2);
    records.add(s3);
    InputClass inputClass = new InputClass(null);
    addRows(records, inputClass);
    assertNotNull(inputClass);
  }
}
