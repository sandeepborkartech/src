package com.lms.app.form;

import org.apache.struts.action.ActionForm;

public class CreateForm
  extends ActionForm
{
  private static final long serialVersionUID = 1L;
  private String customername;
  
  public CreateForm()
  {
    this.customername = null;
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
