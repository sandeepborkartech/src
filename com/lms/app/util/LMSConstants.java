package com.lms.app.util;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LMSConstants
{
  public static String fiscalyear;
  public static String WASH = "1";
  public static String IRON = "2";
  public static String DD = "3";
  public static String WASH_SEQ = "wash_billnum";
  public static String IRON_SEQ = "iron_billnum";
  public static String DD_SEQ = "dd_billnum";
  
  public static void getFiscalYear(Date myDate)
  {
    try
    {
      System.out.println("Inside static block*************");
      SimpleDateFormat sdfyearonly = new SimpleDateFormat("yyyy");
      SimpleDateFormat sdfmonthonly = new SimpleDateFormat("MM");
      SimpleDateFormat sdfdateonly = new SimpleDateFormat("dd");
      
      String stryear = sdfyearonly.format(myDate);
      String strmonth = sdfmonthonly.format(myDate);
      String strday = sdfdateonly.format(myDate);
      int year = Integer.parseInt(stryear);
      int month = Integer.parseInt(strmonth);
      int day = Integer.parseInt(strday);
      Calendar calStart = Calendar.getInstance();
      Calendar calEnd = Calendar.getInstance();
      Calendar calToday = Calendar.getInstance();
      int yearnext = year + 1;
      calStart.set(year, 2, 31);
      calEnd.set(yearnext, 1, 1);
      calToday.set(year, month - 1, day);
      int yearprevious = year - 1;
      System.out.println("Calendar start=>" + new Date(calStart.getTimeInMillis()).toLocaleString());
      System.out.println("Calendar end=>" + new Date(calEnd.getTimeInMillis()).toLocaleString());
      System.out.println("Calendar calToday=>" + new Date(calToday.getTimeInMillis()).toLocaleString());
      System.out.println("if test=>" + ((calToday.after(calStart)) && (calToday.before(calEnd))));
      if ((calToday.after(calStart)) && (calToday.before(calEnd)))
      {
        String strcurrentyear = Integer.toString(year).substring(2);
        String strnextyear = Integer.toString(yearnext).substring(2);
        fiscalyear = strcurrentyear + strnextyear;
        System.out.println(" current year FiscalYear--->" + fiscalyear);
      }
      else
      {
        String strpreviousyear = Integer.toString(yearprevious).substring(2);
        String strcurrentyear = Integer.toString(year).substring(2);
        fiscalyear = strpreviousyear + strcurrentyear;
        System.out.println(" previous year FiscalYear--->" + fiscalyear);
      }
    }
    catch (Exception e)
    {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
