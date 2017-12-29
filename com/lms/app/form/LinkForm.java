package com.lms.app.form;

import org.apache.struts.action.ActionForm;

public class LinkForm
  extends ActionForm
{
  private static final long serialVersionUID = 1L;
  private char actioncalled;
  
  public char getActioncalled()
  {
    return this.actioncalled;
  }
  
  public void setActioncalled(char actioncalled)
  {
    this.actioncalled = actioncalled;
  }
}
