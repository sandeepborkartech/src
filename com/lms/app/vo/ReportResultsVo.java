package com.lms.app.vo;

public class ReportResultsVo
{
  private String billnumber;
  private String billdate;
  private String customername;
  private float billamount;
  private float billpaidamount;
  private String billdesc;
  private int totalCount;
  private String billPaidDate;
  
  public ReportResultsVo()
  {
    this.billnumber = null;
    this.billdate = null;
    this.customername = null;
    this.billamount = 0.0F;
    this.billpaidamount = 0.0F;
    this.billdesc = null;
    this.totalCount = 0;
    this.billPaidDate = null;
  }
  
  public String getBillPaidDate()
  {
    return this.billPaidDate;
  }
  
  public void setBillPaidDate(String billPaidDate)
  {
    this.billPaidDate = billPaidDate;
  }
  
  public int getTotalCount()
  {
    return this.totalCount;
  }
  
  public void setTotalCount(int totalCount)
  {
    this.totalCount = totalCount;
  }
  
  public String getBilldesc()
  {
    return this.billdesc;
  }
  
  public void setBilldesc(String billdesc)
  {
    this.billdesc = billdesc;
  }
  
  public String getBillnumber()
  {
    return this.billnumber;
  }
  
  public void setBillnumber(String billnumber)
  {
    this.billnumber = billnumber;
  }
  
  public String getBilldate()
  {
    return this.billdate;
  }
  
  public void setBilldate(String billdate)
  {
    this.billdate = billdate;
  }
  
  public String getCustomername()
  {
    return this.customername;
  }
  
  public void setCustomername(String customername)
  {
    this.customername = customername;
  }
  
  public float getBillamount()
  {
    return this.billamount;
  }
  
  public void setBillamount(float billamount)
  {
    this.billamount = billamount;
  }
  
  public float getBillpaidamount()
  {
    return this.billpaidamount;
  }
  
  public void setBillpaidamount(float billpaidamount)
  {
    this.billpaidamount = billpaidamount;
  }
}
