package com.lms.app.vo;

public class SearchParamVo
{
  private String billnumber;
  private String billdate;
  private String customername;
  private String homedeli;
  
  public SearchParamVo()
  {
    this.billnumber = "";
    this.billdate = "";
    this.customername = "";
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
  
  public void setHomedeli(String homedeli)
  {
    this.homedeli = homedeli;
  }
  
  public String getHomedeli()
  {
    return this.homedeli;
  }
}
