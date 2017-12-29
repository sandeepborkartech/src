package com.lms.app.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role", catalog="lms")
public class Role
  implements Serializable
{
  private Long roleId;
  private String roleDesc;
  private Set<Users> userses = new HashSet(0);
  
  public Role() {}
  
  public Role(Long roleId)
  {
    this.roleId = roleId;
  }
  
  public Role(Long roleId, String roleDesc, Set<Users> userses)
  {
    this.roleId = roleId;
    this.roleDesc = roleDesc;
    this.userses = userses;
  }
  
  @Id
  @Column(name="Role_id", unique=true, nullable=false, precision=10, scale=0)
  public Long getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(Long roleId)
  {
    this.roleId = roleId;
  }
  
  @Column(name="Role_desc", length=100)
  public String getRoleDesc()
  {
    return this.roleDesc;
  }
  
  public void setRoleDesc(String roleDesc)
  {
    this.roleDesc = roleDesc;
  }
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="role")
  public Set<Users> getUserses()
  {
    return this.userses;
  }
  
  public void setUserses(Set<Users> userses)
  {
    this.userses = userses;
  }
}
