package com.lms.app.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicemaster", catalog="lms")
public class Servicemaster
  implements Serializable
{
  private Integer serviceid;
  private String serviceDesc;
  private String servicetype;
  private String serviceShortDesc;
  
  public Servicemaster() {}
  
  public Servicemaster(Integer serviceid)
  {
    this.serviceid = serviceid;
  }
  
  public Servicemaster(Integer serviceid, String serviceDesc, String servicetype, String serviceShortDesc)
  {
    this.serviceid = serviceid;
    this.serviceDesc = serviceDesc;
    this.servicetype = servicetype;
    this.serviceShortDesc = serviceShortDesc;
  }
  
  @Id
  @Column(name="serviceid", unique=true, nullable=false)
  public Integer getServiceid()
  {
    return this.serviceid;
  }
  
  public void setServiceid(Integer serviceid)
  {
    this.serviceid = serviceid;
  }
  
  @Column(name="service_desc", length=100)
  public String getServiceDesc()
  {
    return this.serviceDesc;
  }
  
  public void setServiceDesc(String serviceDesc)
  {
    this.serviceDesc = serviceDesc;
  }
  
  @Column(name="servicetype", length=10)
  public String getServicetype()
  {
    return this.servicetype;
  }
  
  public void setServicetype(String servicetype)
  {
    this.servicetype = servicetype;
  }
  
  @Column(name="service_short_desc", length=10)
  public String getServiceShortDesc()
  {
    return this.serviceShortDesc;
  }
  
  public void setServiceShortDesc(String serviceShortDesc)
  {
    this.serviceShortDesc = serviceShortDesc;
  }
}
