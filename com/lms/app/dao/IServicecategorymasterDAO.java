package com.lms.app.dao;

import com.lms.app.vo.Servicecategorymaster;
import java.util.List;

public abstract interface IServicecategorymasterDAO
{
  public abstract void save(Servicecategorymaster paramServicecategorymaster);
  
  public abstract void delete(Servicecategorymaster paramServicecategorymaster);
  
  public abstract Servicecategorymaster update(Servicecategorymaster paramServicecategorymaster);
  
  public abstract Servicecategorymaster findById(Integer paramInteger);
  
  public abstract List<Servicecategorymaster> findByProperty(String paramString, Object paramObject);
  
  public abstract List<Servicecategorymaster> findByServcatdesc(Object paramObject);
  
  public abstract List<Servicecategorymaster> findAll();
}
