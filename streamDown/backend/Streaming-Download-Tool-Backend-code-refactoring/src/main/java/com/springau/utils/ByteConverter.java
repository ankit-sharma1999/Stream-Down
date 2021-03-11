package com.springau.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteConverter {

  private ByteConverter() {

  }

  public static byte[] getData(InputClass ipt) throws IOException {
    return toByteArray(ipt.getInput());
  }

  private static byte[] toByteArray(ByteArrayInputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    byte[] buffer = new byte[1024];
    int len;

    // read bytes from the input stream and store them in buffer
    while ((len = in.read(buffer)) != -1) {
      // write bytes from the buffer into output stream
      out.write(buffer, 0, len);
    }
    return out.toByteArray();
  }

}
