package com.lms.app.dao;

import com.lms.app.vo.Servicemaster;
import java.util.List;

public abstract interface IServicemasterDAO
{
  public abstract void save(Servicemaster paramServicemaster);
  
  public abstract void delete(Servicemaster paramServicemaster);
  
  public abstract Servicemaster update(Servicemaster paramServicemaster);
  
  public abstract Servicemaster findById(Integer paramInteger);
  
  public abstract List<Servicemaster> findByProperty(String paramString, Object paramObject);
  
  public abstract List<Servicemaster> findByServiceDesc(Object paramObject);
  
  public abstract List<Servicemaster> findByServicetype(Object paramObject);
  
  public abstract List<Servicemaster> findByServiceShortDesc(Object paramObject);
  
  public abstract List<Servicemaster> findAll();
}
