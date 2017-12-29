package com.lms.app.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LMSDAOFactory
  extends DAOFactory
{
  public static Connection createConnection()
    throws Exception
  {
    Context initContext = new InitialContext();
    DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/LMSDB");
    Connection conn = ds.getConnection();
    if (conn != null) {
      return conn;
    }
    throw new Exception("Error in getting database connection");
  }
  
  public LMSLoginDAO getLMSLoginDAO()
  {
    return new LMSLoginDAO();
  }
  
  public LMSBillingDAO getLMSBillingDAO()
  {
    return new LMSBillingDAO();
  }
  
  public LMSAdminDAO getLMSAdminDAO()
  {
    return new LMSAdminDAO();
  }
}
