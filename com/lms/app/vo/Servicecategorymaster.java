package com.lms.app.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicecategorymaster", catalog="lms")
public class Servicecategorymaster
  implements Serializable
{
  private Integer servcatid;
  private String servcatdesc;
  
  public Servicecategorymaster() {}
  
  public Servicecategorymaster(Integer servcatid)
  {
    this.servcatid = servcatid;
  }
  
  public Servicecategorymaster(Integer servcatid, String servcatdesc)
  {
    this.servcatid = servcatid;
    this.servcatdesc = servcatdesc;
  }
  
  @Id
  @Column(name="servcatid", unique=true, nullable=false)
  public Integer getServcatid()
  {
    return this.servcatid;
  }
  
  public void setServcatid(Integer servcatid)
  {
    this.servcatid = servcatid;
  }
  
  @Column(name="servcatdesc", length=400)
  public String getServcatdesc()
  {
    return this.servcatdesc;
  }
  
  public void setServcatdesc(String servcatdesc)
  {
    this.servcatdesc = servcatdesc;
  }
}
