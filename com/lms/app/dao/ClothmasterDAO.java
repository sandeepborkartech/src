package com.lms.app.dao;

import com.lms.app.vo.Clothmaster;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClothmasterDAO
  implements IClothmasterDAO
{
  public static final String CLOTH_DESC = "clothDesc";
  
  private EntityManager getEntityManager()
  {
    return EntityManagerHelper.getEntityManager();
  }
  
  public void save(Clothmaster entity)
  {
    EntityManagerHelper.log("saving Clothmaster instance", Level.INFO, null);
    try
    {
      getEntityManager().persist(entity);
      EntityManagerHelper.log("save successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("save failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public void delete(Clothmaster entity)
  {
    EntityManagerHelper.log("deleting Clothmaster instance", Level.INFO, 
      null);
    try
    {
      entity = (Clothmaster)getEntityManager().getReference(Clothmaster.class, 
        entity.getClothid());
      getEntityManager().remove(entity);
      EntityManagerHelper.log("delete successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("delete failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Clothmaster update(Clothmaster entity)
  {
    EntityManagerHelper.log("updating Clothmaster instance", Level.INFO, 
      null);
    try
    {
      Clothmaster result = (Clothmaster)getEntityManager().merge(entity);
      EntityManagerHelper.log("update successful", Level.INFO, null);
      return result;
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("update failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Clothmaster findById(Integer id)
  {
    EntityManagerHelper.log("finding Clothmaster instance with id: " + id, 
      Level.INFO, null);
    try
    {
      return (Clothmaster)getEntityManager().find(Clothmaster.class, 
        id);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Clothmaster> findByProperty(String propertyName, Object value)
  {
    EntityManagerHelper.log(
      "finding Clothmaster instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
    try
    {
      String queryString = "select model from Clothmaster model where model." + 
        propertyName + "= :propertyValue";
      Query query = getEntityManager().createQuery(queryString);
      query.setParameter("propertyValue", value);
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find by property name failed", 
        Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Clothmaster> findByClothDesc(Object clothDesc)
  {
    return findByProperty("clothDesc", clothDesc);
  }
  
  public List<Clothmaster> findAll()
  {
    EntityManagerHelper.log("finding all Clothmaster instances", 
      Level.INFO, null);
    try
    {
      String queryString = "select model from Clothmaster model";
      Query query = getEntityManager().createQuery("select model from Clothmaster model");
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find all failed", Level.SEVERE, re);
      throw re;
    }
  }
}
