package com.lms.app.dao;

import com.lms.app.vo.Users;
import java.util.List;

public abstract interface IUsersDAO
{
  public abstract void save(Users paramUsers);
  
  public abstract void delete(Users paramUsers);
  
  public abstract Users update(Users paramUsers);
  
  public abstract Users findById(Long paramLong);
  
  public abstract List<Users> findByProperty(String paramString, Object paramObject);
  
  public abstract List<Users> findByUsername(Object paramObject);
  
  public abstract List<Users> findByPassword(Object paramObject);
  
  public abstract List<Users> findByFname(Object paramObject);
  
  public abstract List<Users> findByLname(Object paramObject);
  
  public abstract List<Users> findByWrongPasswordCount(Object paramObject);
  
  public abstract List<Users> findByIslocked(Object paramObject);
  
  public abstract List<Users> findAll();
}
