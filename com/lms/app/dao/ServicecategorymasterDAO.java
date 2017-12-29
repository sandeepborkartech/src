package com.lms.app.dao;

import com.lms.app.vo.Servicecategorymaster;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServicecategorymasterDAO
  implements IServicecategorymasterDAO
{
  public static final String SERVCATDESC = "servcatdesc";
  
  private EntityManager getEntityManager()
  {
    return EntityManagerHelper.getEntityManager();
  }
  
  public void save(Servicecategorymaster entity)
  {
    EntityManagerHelper.log("saving Servicecategorymaster instance", 
      Level.INFO, null);
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
  
  public void delete(Servicecategorymaster entity)
  {
    EntityManagerHelper.log("deleting Servicecategorymaster instance", 
      Level.INFO, null);
    try
    {
      entity = (Servicecategorymaster)getEntityManager().getReference(
        Servicecategorymaster.class, entity.getServcatid());
      getEntityManager().remove(entity);
      EntityManagerHelper.log("delete successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("delete failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Servicecategorymaster update(Servicecategorymaster entity)
  {
    EntityManagerHelper.log("updating Servicecategorymaster instance", 
      Level.INFO, null);
    try
    {
      Servicecategorymaster result = (Servicecategorymaster)getEntityManager().merge(entity);
      EntityManagerHelper.log("update successful", Level.INFO, null);
      return result;
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("update failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Servicecategorymaster findById(Integer id)
  {
    EntityManagerHelper.log(
      "finding Servicecategorymaster instance with id: " + id, 
      Level.INFO, null);
    try
    {
      return (Servicecategorymaster)getEntityManager().find(
        Servicecategorymaster.class, id);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Servicecategorymaster> findByProperty(String propertyName, Object value)
  {
    EntityManagerHelper.log(
    
      "finding Servicecategorymaster instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
    try
    {
      String queryString = "select model from Servicecategorymaster model where model." + 
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
  
  public List<Servicecategorymaster> findByServcatdesc(Object servcatdesc)
  {
    return findByProperty("servcatdesc", servcatdesc);
  }
  
  public List<Servicecategorymaster> findAll()
  {
    EntityManagerHelper.log("finding all Servicecategorymaster instances", 
      Level.INFO, null);
    try
    {
      String queryString = "select model from Servicecategorymaster model";
      Query query = getEntityManager().createQuery("select model from Servicecategorymaster model");
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find all failed", Level.SEVERE, re);
      throw re;
    }
  }
}
