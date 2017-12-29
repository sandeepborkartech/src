package com.lms.app.vo;

import java.util.ArrayList;

public class BillVo
{
  private String billnumber;
  private String serviceType;
  private String CustomerName;
  private String billdate;
  private Float billamount;
  private String billPaid;
  private String billPaidDate;
  private String billDelivered;
  private String billDeliveredDate;
  private Float billpaidamount;
  private String splcomments;
  private Float advPay;
  private int totalCount;
  private ArrayList arrBillDetails;
  private String billcrtime;
  private String homedeli;
  
  public String getHomedeli()
  {
    return this.homedeli;
  }
  
  public void setHomedeli(String homedeli)
  {
    this.homedeli = homedeli;
  }
  
  public String getBillcrtime()
  {
    return this.billcrtime;
  }
  
  public void setBillcrtime(String billcrtime)
  {
    this.billcrtime = billcrtime;
  }
  
  public BillVo()
  {
    this.billnumber = null;
    this.serviceType = null;
    this.CustomerName = null;
    this.billdate = null;
    this.billamount = Float.valueOf(0.0F);
    this.billPaid = null;
    this.billPaidDate = null;
    this.billDelivered = null;
    this.billDeliveredDate = null;
    this.billpaidamount = Float.valueOf(0.0F);
    this.splcomments = null;
    this.advPay = Float.valueOf(0.0F);
    this.totalCount = 0;
    this.billcrtime = null;
  }
  
  public String getBillnumber()
  {
    return this.billnumber;
  }
  
  public void setBillnumber(String billnumber)
  {
    this.billnumber = billnumber;
  }
  
  public String getServiceType()
  {
    return this.serviceType;
  }
  
  public void setServiceType(String serviceType)
  {
    this.serviceType = serviceType;
  }
  
  public String getCustomerName()
  {
    return this.CustomerName;
  }
  
  public void setCustomerName(String strCustomerName)
  {
    this.CustomerName = strCustomerName;
  }
  
  public String getBilldate()
  {
    return this.billdate;
  }
  
  public void setBilldate(String billdate)
  {
    this.billdate = billdate;
  }
  
  public Float getBillamount()
  {
    return this.billamount;
  }
  
  public void setBillamount(Float billamount)
  {
    this.billamount = billamount;
  }
  
  public String getBillPaid()
  {
    return this.billPaid;
  }
  
  public void setBillPaid(String billPaid)
  {
    this.billPaid = billPaid;
  }
  
  public String getBillPaidDate()
  {
    return this.billPaidDate;
  }
  
  public void setBillPaidDate(String billPaidDate)
  {
    this.billPaidDate = billPaidDate;
  }
  
  public String getBillDelivered()
  {
    return this.billDelivered;
  }
  
  public void setBillDelivered(String billDelivered)
  {
    this.billDelivered = billDelivered;
  }
  
  public String getBillDeliveredDate()
  {
    return this.billDeliveredDate;
  }
  
  public void setBillDeliveredDate(String billDeliveredDate)
  {
    this.billDeliveredDate = billDeliveredDate;
  }
  
  public Float getBillpaidamount()
  {
    return this.billpaidamount;
  }
  
  public void setBillpaidamount(Float billpaidamount)
  {
    this.billpaidamount = billpaidamount;
  }
  
  public String getSplcomments()
  {
    return this.splcomments;
  }
  
  public void setSplcomments(String splcomments)
  {
    this.splcomments = splcomments;
  }
  
  public Float getAdvPay()
  {
    return this.advPay;
  }
  
  public void setAdvPay(Float advPay)
  {
    this.advPay = advPay;
  }
  
  public int getTotalCount()
  {
    return this.totalCount;
  }
  
  public void setTotalCount(int totalCount)
  {
    this.totalCount = totalCount;
  }
  
  public ArrayList getArrBillDetails()
  {
    return this.arrBillDetails;
  }
  
  public void setArrBillDetails(ArrayList arrBillDetails)
  {
    this.arrBillDetails = arrBillDetails;
  }
}
