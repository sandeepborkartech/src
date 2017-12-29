package com.lms.app.vo;

public class LoginVo
{
  private String username;
  private String password;
  private String fname;
  private String lname;
  private String lastlogin;
  private String loginsuccess;
  private String rolename;
  
  public LoginVo()
  {
    this.username = null;
    this.password = null;
    this.fname = null;
    this.lastlogin = null;
    this.loginsuccess = null;
    this.rolename = null;
  }
  
  public String getLoginsuccess()
  {
    return this.loginsuccess;
  }
  
  public void setLoginsuccess(String loginsuccess)
  {
    this.loginsuccess = loginsuccess;
  }
  
  public String getFname()
  {
    return this.fname;
  }
  
  public void setFname(String fname)
  {
    this.fname = fname;
  }
  
  public String getLastlogin()
  {
    return this.lastlogin;
  }
  
  public void setLastlogin(String lastlogin)
  {
    this.lastlogin = lastlogin;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getRolename()
  {
    return this.rolename;
  }
  
  public void setRolename(String rolename)
  {
    this.rolename = rolename;
  }
  
  public void setLname(String lname)
  {
    this.lname = lname;
  }
  
  public String getLname()
  {
    return this.lname;
  }
}
