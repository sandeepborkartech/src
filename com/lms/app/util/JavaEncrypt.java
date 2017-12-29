package com.lms.app.util;

import java.io.PrintStream;

public class JavaEncrypt
{
  public static String encode(String base)
  {
    byte[] rawData = base.getBytes();
    StringBuffer hexText = new StringBuffer();
    String initialHex = null;
    int initHexLength = 0;
    for (int i = 0; i < rawData.length; i++)
    {
      int positiveValue = rawData[i] & 0xFF;
      initialHex = Integer.toHexString(positiveValue);
      for (initHexLength = initialHex.length(); initHexLength++ < 2;) {
        hexText.append("0");
      }
      hexText.append(initialHex);
    }
    return hexText.toString();
  }
  
  public static String decode(String hexText)
  {
    String decodedText = null;
    String chunk = null;
    if ((hexText != null) && (hexText.length() > 0))
    {
      int numBytes = hexText.length() / 2;
      byte[] rawToByte = new byte[numBytes];
      int offset = 0;
      for (int i = 0; i < numBytes; i++)
      {
        chunk = hexText.substring(offset, offset + 2);
        offset += 2;
        rawToByte[i] = ((byte)(Integer.parseInt(chunk, 16) & 0xFF));
      }
      decodedText = new String(rawToByte);
    }
    return decodedText;
  }
  
  public static void main(String[] args)
  {
    String s = "counter";
    System.out.println(s);
    System.out.println("hexa value-->" + encode(s));
    System.out.println("Orignal String--" + decode(encode(s)));
  }
}
