package com.lms.app.vo;

import com.lms.app.util.LMSUtility;

public class ReportCriteriaVo
{
  private String tobilldate;
  private String frombilldate;
  private String topaiddate;
  private String frompaiddate;
  private String customername;
  private String paystatus;
  private String servicetype;
  
  public String getTopaiddate()
  {
    return this.topaiddate;
  }
  
  public void setTopaiddate(String topaiddate)
  {
    String toDate;
    //String toDate;
    if ((topaiddate == "") || (topaiddate == null)) {
      toDate = topaiddate;
    } else {
      toDate = LMSUtility.DBformatDate(topaiddate);
    }
    this.topaiddate = toDate;
  }
  
  public String getFrompaiddate()
  {
    return this.frompaiddate;
  }
  
  public void setFrompaiddate(String frompaiddate)
  {
    String fromDate;
    //String fromDate;
    if ((frompaiddate == "") || (frompaiddate == null)) {
      fromDate = frompaiddate;
    } else {
      fromDate = LMSUtility.DBformatDate(frompaiddate);
    }
    this.frompaiddate = fromDate;
  }
  
  public ReportCriteriaVo()
  {
    this.tobilldate = null;
    this.frombilldate = null;
    this.customername = null;
    this.paystatus = null;
    this.servicetype = null;
  }
  
  public String getTobilldate()
  {
    return this.tobilldate;
  }
  
  public void setTobilldate(String tobilldate)
  {
    String toDate;
    //String toDate;
    if ((tobilldate == "") || (tobilldate == null)) {
      toDate = tobilldate;
    } else {
      toDate = LMSUtility.DBformatDate(tobilldate);
    }
    this.tobilldate = toDate;
  }
  
  public String getFrombilldate()
  {
    return this.frombilldate;
  }
  
  public void setFrombilldate(String frombilldate)
  {
    String fromDate;
    //String fromDate;
    if ((frombilldate == "") || (frombilldate == null)) {
      fromDate = frombilldate;
    } else {
      fromDate = LMSUtility.DBformatDate(frombilldate);
    }
    this.frombilldate = fromDate;
  }
  
  public String getPaystatus()
  {
    return this.paystatus;
  }
  
  public void setPaystatus(String paystatus)
  {
    this.paystatus = paystatus;
  }
  
  public String getServicetype()
  {
    return this.servicetype;
  }
  
  public void setServicetype(String servicetype)
  {
    this.servicetype = servicetype;
  }
  
  public String getCustomername()
  {
    return this.customername;
  }
  
  public void setCustomername(String customername)
  {
    this.customername = customername;
  }
}
