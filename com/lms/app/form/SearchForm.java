package com.lms.app.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;

public class SearchForm
  extends ActionForm
{
  private static final long serialVersionUID = 1L;
  private String billnum;
  private String customername;
  private Date billdate;
  
  public SearchForm()
  {
    this.billnum = null;
    this.customername = null;
    this.billdate = null;
  }
  
  public Date getBilldate()
  {
    return this.billdate;
  }
  
  public void setBilldate(Date billdate)
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
  
  public String getBillNumber()
  {
    return this.billnum;
  }
  
  public void setBillNumber(String billnum)
  {
    this.billnum = billnum;
  }
}
