package com.lms.app.util;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LMSUtility
{
  public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  public static SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
  
  public static String UIAmountFormat(float amountValue)
  {
    NumberFormat formatter = new DecimalFormat("#0.00");
    return formatter.format(amountValue);
  }
  
  public static String getCurrentDate()
    throws Exception
  {
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    String billDate = billingDAO.getBillDate();
    return billDate;
  }
  
  public static String UIformatDate(String str_date)
  {
    String new_str_date = null;
    try
    {
      if ((str_date != null) && (str_date != ""))
      {
        DateFormat dateFormat = sdf;
        DateFormat dateFormat1 = sdf1;
        Date d = dateFormat.parse(str_date);
        new_str_date = dateFormat1.format(d);
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return new_str_date;
  }
  
  public static String DBformatDate(String str_date)
  {
    String new_str_date = null;
    try
    {
      if ((str_date == null) || (str_date == ""))
      {
        str_date = "";
      }
      else
      {
        DateFormat dateFormat = sdf1;
        DateFormat dateFormat1 = sdf;
        Date d = dateFormat.parse(str_date);
        new_str_date = dateFormat1.format(d);
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return new_str_date;
  }
  
  public static String getCreateBillNumber(int lmssequence, String servicetype)
  {
    StringBuffer strbCreateSeq = new StringBuffer();
    if ("1".equals(servicetype)) {
      strbCreateSeq.append("W");
    } else if ("2".equals(servicetype)) {
      strbCreateSeq.append("I");
    } else if ("3".equals(servicetype)) {
      strbCreateSeq.append("D");
    }
    strbCreateSeq.append(LMSConstants.fiscalyear);
    return strbCreateSeq.toString();
  }
  
  public static void main(String[] args)
  {
    float val = 10.4F;
    System.out.println("Value--" + val);
    String outval = UIAmountFormat(val);
    System.out.println("Out Value--" + outval);
  }
}
