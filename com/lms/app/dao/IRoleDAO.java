package com.lms.app.dao;

import com.lms.app.vo.Role;
import java.util.List;

public abstract interface IRoleDAO
{
  public abstract void save(Role paramRole);
  
  public abstract void delete(Role paramRole);
  
  public abstract Role update(Role paramRole);
  
  public abstract Role findById(Long paramLong);
  
  public abstract List<Role> findByProperty(String paramString, Object paramObject);
  
  public abstract List<Role> findByRoleDesc(Object paramObject);
  
  public abstract List<Role> findAll();
}
