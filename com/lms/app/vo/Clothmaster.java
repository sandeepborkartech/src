package com.lms.app.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clothmaster", catalog="lms")
public class Clothmaster
  implements Serializable
{
  private Integer clothid;
  private String clothDesc;
  
  public Clothmaster() {}
  
  public Clothmaster(Integer clothid)
  {
    this.clothid = clothid;
  }
  
  public Clothmaster(Integer clothid, String clothDesc)
  {
    this.clothid = clothid;
    this.clothDesc = clothDesc;
  }
  
  @Id
  @Column(name="clothid", unique=true, nullable=false)
  public Integer getClothid()
  {
    return this.clothid;
  }
  
  public void setClothid(Integer clothid)
  {
    this.clothid = clothid;
  }
  
  @Column(name="cloth_desc", length=100)
  public String getClothDesc()
  {
    return this.clothDesc;
  }
  
  public void setClothDesc(String clothDesc)
  {
    this.clothDesc = clothDesc;
  }
}
