package com.lms.app.dao;

public abstract class DAOFactory
{
  public static final int LMSDAO = 1;
  
  public abstract LMSLoginDAO getLMSLoginDAO();
  
  public abstract LMSBillingDAO getLMSBillingDAO();
  
  public abstract LMSAdminDAO getLMSAdminDAO();
  
  public static DAOFactory getDAOFactory(int whichFactory)
  {
    switch (whichFactory)
    {
    case 1: 
      return new LMSDAOFactory();
    }
    return null;
  }
}
