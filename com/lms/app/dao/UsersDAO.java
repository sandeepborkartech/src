package com.lms.app.dao;

import com.lms.app.vo.Users;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsersDAO
  implements IUsersDAO
{
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String FNAME = "fname";
  public static final String LNAME = "lname";
  public static final String WRONG_PASSWORD_COUNT = "wrongPasswordCount";
  public static final String ISLOCKED = "islocked";
  
  private EntityManager getEntityManager()
  {
    return EntityManagerHelper.getEntityManager();
  }
  
  public void save(Users entity)
  {
    EntityManagerHelper.log("saving Users instance", Level.INFO, null);
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
  
  public void delete(Users entity)
  {
    EntityManagerHelper.log("deleting Users instance", Level.INFO, null);
    try
    {
      entity = (Users)getEntityManager().getReference(Users.class, 
        entity.getUserId());
      getEntityManager().remove(entity);
      EntityManagerHelper.log("delete successful", Level.INFO, null);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("delete failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Users update(Users entity)
  {
    EntityManagerHelper.log("updating Users instance", Level.INFO, null);
    try
    {
      Users result = (Users)getEntityManager().merge(entity);
      EntityManagerHelper.log("update successful", Level.INFO, null);
      return result;
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("update failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public Users findById(Long id)
  {
    EntityManagerHelper.log("finding Users instance with id: " + id, 
      Level.INFO, null);
    try
    {
      return (Users)getEntityManager().find(Users.class, id);
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find failed", Level.SEVERE, re);
      throw re;
    }
  }
  
  public List<Users> findByProperty(String propertyName, Object value)
  {
    EntityManagerHelper.log(
      "finding Users instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
    try
    {
      String queryString = "select model from Users model where model." + 
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
  
  public List<Users> findByUsername(Object username)
  {
    return findByProperty("username", username);
  }
  
  public List<Users> findByPassword(Object password)
  {
    return findByProperty("password", password);
  }
  
  public List<Users> findByFname(Object fname)
  {
    return findByProperty("fname", fname);
  }
  
  public List<Users> findByLname(Object lname)
  {
    return findByProperty("lname", lname);
  }
  
  public List<Users> findByWrongPasswordCount(Object wrongPasswordCount)
  {
    return findByProperty("wrongPasswordCount", wrongPasswordCount);
  }
  
  public List<Users> findByIslocked(Object islocked)
  {
    return findByProperty("islocked", islocked);
  }
  
  public List<Users> findAll()
  {
    EntityManagerHelper.log("finding all Users instances", Level.INFO, null);
    try
    {
      String queryString = "select model from Users model";
      Query query = getEntityManager().createQuery("select model from Users model");
      return query.getResultList();
    }
    catch (RuntimeException re)
    {
      EntityManagerHelper.log("find all failed", Level.SEVERE, re);
      throw re;
    }
  }
}
