package com.lms.app.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users", catalog="lms")
public class Users
  implements Serializable
{
  private Long userId;
  private Role role;
  private String username;
  private String password;
  private String fname;
  private String lname;
  private Date createdDate;
  private Date lastLoginDate;
  private Long wrongPasswordCount;
  private Date passwordExpiryDate;
  private String islocked;
  
  public Users() {}
  
  public Users(Long userId)
  {
    this.userId = userId;
  }
  
  public Users(Long userId, Role role, String username, String password, String fname, String lname, Date createdDate, Date lastLoginDate, Long wrongPasswordCount, Date passwordExpiryDate, String islocked)
  {
    this.userId = userId;
    this.role = role;
    this.username = username;
    this.password = password;
    this.fname = fname;
    this.lname = lname;
    this.createdDate = createdDate;
    this.lastLoginDate = lastLoginDate;
    this.wrongPasswordCount = wrongPasswordCount;
    this.passwordExpiryDate = passwordExpiryDate;
    this.islocked = islocked;
  }
  
  @Id
  @Column(name="user_id", unique=true, nullable=false, precision=10, scale=0)
  public Long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Long userId)
  {
    this.userId = userId;
  }
  
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="Role_id")
  public Role getRole()
  {
    return this.role;
  }
  
  public void setRole(Role role)
  {
    this.role = role;
  }
  
  @Column(name="username", length=30)
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  @Column(name="password", length=500)
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  @Column(name="fname", length=100)
  public String getFname()
  {
    return this.fname;
  }
  
  public void setFname(String fname)
  {
    this.fname = fname;
  }
  
  @Column(name="lname", length=100)
  public String getLname()
  {
    return this.lname;
  }
  
  public void setLname(String lname)
  {
    this.lname = lname;
  }
  
  @Temporal(TemporalType.DATE)
  @Column(name="created_date", length=10)
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  @Temporal(TemporalType.DATE)
  @Column(name="last_login_date", length=10)
  public Date getLastLoginDate()
  {
    return this.lastLoginDate;
  }
  
  public void setLastLoginDate(Date lastLoginDate)
  {
    this.lastLoginDate = lastLoginDate;
  }
  
  @Column(name="wrong_password_count", precision=10, scale=0)
  public Long getWrongPasswordCount()
  {
    return this.wrongPasswordCount;
  }
  
  public void setWrongPasswordCount(Long wrongPasswordCount)
  {
    this.wrongPasswordCount = wrongPasswordCount;
  }
  
  @Temporal(TemporalType.DATE)
  @Column(name="password_expiry_date", length=10)
  public Date getPasswordExpiryDate()
  {
    return this.passwordExpiryDate;
  }
  
  public void setPasswordExpiryDate(Date passwordExpiryDate)
  {
    this.passwordExpiryDate = passwordExpiryDate;
  }
  
  @Column(name="islocked", length=1)
  public String getIslocked()
  {
    return this.islocked;
  }
  
  public void setIslocked(String islocked)
  {
    this.islocked = islocked;
  }
}
