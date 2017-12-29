package com.lms.app.dao;

import com.lms.app.vo.Servicemaster;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServicemasterDAO
  implements IServicemasterDAO
{
  public static final String SERVICE_DESC = "serviceDesc";
  public static final String SERVICETYPE = "servicetype";
  public static final String SERVICE_SHORT_DESC = "serviceShortDesc";
  
  private EntityManager getEntityManager()
  {
    return EntityManagerHelper.getEntityManager();
  }
  
  public void save(Servicemaster entity)
  {
    EntityManagerHelper.log("saving Servicemaster instance", Level.INFO, 
      null);
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
  
  public void delete(Servicemaster entity)
  {
    EntityManagerHelper.log("deleting Servicemaster instance", Level.INFO, 
      null);
    try
    {
      entity = (Servicemaster)getEntityManager().getReference(Servicemaster.class, 
        entity.getServiceid());
      getEntityManager().remove(entity);
      EntityManagerHelper.log("delete successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("delete failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Servicemaster update(Servicemaster entity)
  {
    EntityManagerHelper.log("updating Servicemaster instance", Level.INFO, 
      null);
    try
    {
      Servicemaster result = (Servicemaster)getEntityManager().merge(entity);
      EntityManagerHelper.log("update successful", Level.INFO, null);
      return result;
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("update failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Servicemaster findById(Integer id)
  {
    EntityManagerHelper.log(
      "finding Servicemaster instance with id: " + id, Level.INFO, 
      null);
    try
    {
      return (Servicemaster)getEntityManager().find(
        Servicemaster.class, id);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Servicemaster> findByProperty(String propertyName, Object value)
  {
    EntityManagerHelper.log(
    
      "finding Servicemaster instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
    try
    {
      String queryString = "select model from Servicemaster model where model." + 
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
  
  public List<Servicemaster> findByServiceDesc(Object serviceDesc)
  {
    return findByProperty("serviceDesc", serviceDesc);
  }
  
  public List<Servicemaster> findByServicetype(Object servicetype)
  {
    return findByProperty("servicetype", servicetype);
  }
  
  public List<Servicemaster> findByServiceShortDesc(Object serviceShortDesc)
  {
    return findByProperty("serviceShortDesc", serviceShortDesc);
  }
  
  public List<Servicemaster> findAll()
  {
    EntityManagerHelper.log("finding all Servicemaster instances", 
      Level.INFO, null);
    try
    {
      String queryString = "select model from Servicemaster model";
      Query query = getEntityManager().createQuery("select model from Servicemaster model");
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find all failed", Level.SEVERE, re);
      throw re;
    }
  }
}
