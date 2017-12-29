package com.lms.app.dao;

import com.lms.app.vo.Role;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RoleDAO
  implements IRoleDAO
{
  public static final String ROLE_DESC = "roleDesc";
  
  private EntityManager getEntityManager()
  {
    return EntityManagerHelper.getEntityManager();
  }
  
  public void save(Role entity)
  {
    EntityManagerHelper.log("saving Role instance", Level.INFO, null);
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
  
  public void delete(Role entity)
  {
    EntityManagerHelper.log("deleting Role instance", Level.INFO, null);
    try
    {
      entity = (Role)getEntityManager().getReference(Role.class, 
        entity.getRoleId());
      getEntityManager().remove(entity);
      EntityManagerHelper.log("delete successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("delete failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Role update(Role entity)
  {
    EntityManagerHelper.log("updating Role instance", Level.INFO, null);
    try
    {
      Role result = (Role)getEntityManager().merge(entity);
      EntityManagerHelper.log("update successful", Level.INFO, null);
      return result;
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("update failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Role findById(Long id)
  {
    EntityManagerHelper.log("finding Role instance with id: " + id, 
      Level.INFO, null);
    try
    {
      return (Role)getEntityManager().find(Role.class, id);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Role> findByProperty(String propertyName, Object value)
  {
    EntityManagerHelper.log(
      "finding Role instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
    try
    {
      String queryString = "select model from Role model where model." + 
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
  
  public List<Role> findByRoleDesc(Object roleDesc)
  {
    return findByProperty("roleDesc", roleDesc);
  }
  
  public List<Role> findAll()
  {
    EntityManagerHelper.log("finding all Role instances", Level.INFO, null);
    try
    {
      String queryString = "select model from Role model";
      Query query = getEntityManager().createQuery("select model from Role model");
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find all failed", Level.SEVERE, re);
      throw re;
    }
  }
}
