package com.lms.app.dao;

import com.lms.app.util.JavaEncrypt;
import com.lms.app.vo.LoginVo;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class LMSLoginDAO
  implements LoginDAO
{
  public static final String strLoginQuery = "Select fname, last_login_date from Users where username=? and password=?";
  public static final String strClothMasterQuery = "Select * from ClothMaster";
  public static final String strServiceMasterQuery = "Select * from ServiceMaster";
  public static final String strServiceTypeMasterQuery = "Select * from ServiceCategoryMaster";
  
  public LoginVo login(LoginVo loginVo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    PreparedStatement pstm = conn.prepareStatement("Select u.fname, u.last_login_date,r.role_desc from Users u,role r where username=? and password=? and u.role_id = r.role_id");
    pstm.setString(1, loginVo.getUsername());
    System.out.println("Value from object" + loginVo.getPassword());
    String value = JavaEncrypt.encode(loginVo.getPassword());
    pstm.setString(2, value);
    ResultSet rst = pstm.executeQuery();
    LoginVo loginvo = new LoginVo();
    if (rst == null)
    {
      System.out.println("Could not login, system is not working properly");
      loginvo.setLoginsuccess("failure");
    }
    else if (rst.next())
    {
      if ((rst.getString(1) != null) && (!"".equalsIgnoreCase(rst.getString(1))))
      {
        loginvo.setFname(rst.getString(1));
        loginvo.setLastlogin(rst.getString(2));
        loginvo.setRolename(rst.getString(3));
        loginvo.setLoginsuccess("success");
      }
      else
      {
        loginvo.setLoginsuccess("failure");
      }
    }
    else
    {
      loginvo.setLoginsuccess("failure");
    }
    rst.close();
    pstm.close();
    conn.close();
    return loginvo;
  }
  
  public ArrayList getLMSMasterData()
    throws Exception
  {
    String[] strLMSMasterData = new String[3];
    Connection conn = LMSDAOFactory.createConnection();
    PreparedStatement pstm = conn.prepareStatement("Select * from ClothMaster");
    ResultSet rst = pstm.executeQuery();
    ArrayList arrMaster = new ArrayList();
    if (rst == null) {
      throw new Exception("Error in getting Cloth Master Data, database issue");
    }
    StringBuffer strbClothList = new StringBuffer();
    while (rst.next())
    {
      strbClothList.append(rst.getInt(1));
      strbClothList.append(":");
      strbClothList.append(rst.getString(2));
      if (!rst.isLast()) {
        strbClothList.append(",");
      }
    }
    rst.close();
    pstm.close();
    strLMSMasterData[0] = strbClothList.toString();
    StringBuffer strbServiceList = new StringBuffer();
    pstm = conn.prepareStatement("Select * from ServiceMaster");
    rst = pstm.executeQuery();
    HashMap hstServiceShortDesc = new HashMap();
    if (rst == null) {
      throw new Exception("Error in getting Cloth Master Data, database issue");
    }
    while (rst.next())
    {
      strbServiceList.append(rst.getInt(1));
      strbServiceList.append(":");
      strbServiceList.append(rst.getString(2));
      strbServiceList.append("+++");
      strbServiceList.append(rst.getString(3));
      hstServiceShortDesc.put(new Integer(rst.getInt(1)).toString(), rst.getString(4));
      if (!rst.isLast()) {
        strbServiceList.append(",");
      }
    }
    pstm.close();
    rst.close();
    strLMSMasterData[1] = strbServiceList.toString();
    pstm = conn.prepareStatement("Select * from ServiceCategoryMaster");
    rst = pstm.executeQuery();
    if (rst == null) {
      throw new Exception("Error in getting Service Master Data, database issue");
    }
    StringBuffer strbservtypeList = new StringBuffer();
    while (rst.next())
    {
      strbservtypeList.append(rst.getInt(1));
      strbservtypeList.append(":");
      strbservtypeList.append(rst.getString(2));
      if (!rst.isLast()) {
        strbservtypeList.append(",");
      }
    }
    rst.close();
    pstm.close();
    strLMSMasterData[2] = strbservtypeList.toString();
    conn.close();
    arrMaster.add(strLMSMasterData);
    arrMaster.add(hstServiceShortDesc);
    return arrMaster;
  }
}
