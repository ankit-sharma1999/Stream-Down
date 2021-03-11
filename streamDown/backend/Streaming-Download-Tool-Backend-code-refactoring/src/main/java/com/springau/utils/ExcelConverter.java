package com.springau.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import com.springau.dtos.ExcelHeaderConfig;

@Service
public class ExcelConverter {

  private Workbook workbook;
  private ByteArrayOutputStream output;
  private Sheet sheet;
  private int rownum = 1;

  public void createHeader(ExcelHeaderConfig headerConfig) {
    workbook = new SXSSFWorkbook();
    output = new ByteArrayOutputStream();
    sheet = workbook.createSheet(headerConfig.getSheetName());
    Row headerRow = sheet.createRow(0);

    List<String> headers = headerConfig.getHeaders();
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

}
