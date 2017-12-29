package com.lms.app.vo;

import com.lms.app.util.LMSUtility;

public class AdminUpdateMultiBillsCriteriaVo
{
  private String tobilldate;
  private String frombilldate;
  private String tobillnum;
  private String frombillnum;
  private String paystatus;
  private String billlist;
  
  public AdminUpdateMultiBillsCriteriaVo()
  {
    this.tobilldate = null;
    this.frombilldate = null;
    this.tobillnum = null;
    this.frombillnum = null;
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
  
  public void setTobillnum(String tobillnum)
  {
    this.tobillnum = tobillnum;
  }
  
  public String getTobillnum()
  {
    return this.tobillnum;
  }
  
  public String getFrombillnum()
  {
    return this.frombillnum;
  }
  
  public void setFrombillnum(String frombillnum)
  {
    this.frombillnum = frombillnum;
  }
  
  public String getPaystatus()
  {
    return this.paystatus;
  }
  
  public void setPaystatus(String paystatus)
  {
    this.paystatus = paystatus;
  }
  
  public void setBilllist(String billlist)
  {
    this.billlist = billlist;
  }
  
  public String getBilllist()
  {
    return this.billlist;
  }
}
