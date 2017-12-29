package com.lms.app.dao;

import com.lms.app.vo.Clothmaster;
import java.util.List;

public abstract interface IClothmasterDAO
{
  public abstract void save(Clothmaster paramClothmaster);
  
  public abstract void delete(Clothmaster paramClothmaster);
  
  public abstract Clothmaster update(Clothmaster paramClothmaster);
  
  public abstract Clothmaster findById(Integer paramInteger);
  
  public abstract List<Clothmaster> findByProperty(String paramString, Object paramObject);
  
  public abstract List<Clothmaster> findByClothDesc(Object paramObject);
  
  public abstract List<Clothmaster> findAll();
}
