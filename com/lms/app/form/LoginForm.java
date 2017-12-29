package com.lms.app.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm
  extends ActionForm
{
  private static final long serialVersionUID = -473562596852452021L;
  private String userName;
  private String password;
  
  public LoginForm()
  {
    this.userName = null;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    ActionErrors actionErrors = new ActionErrors();
    if ((this.userName == null) || (this.userName.trim().equals(""))) {
      actionErrors.add("userName", new ActionMessage("error.username"));
    }
    try
    {
      if ((this.password == null) || (this.password.trim().equals(""))) {
        actionErrors.add("password", new ActionMessage("error.password"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return actionErrors;
  }
}
