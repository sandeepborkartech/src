package com.lms.app.action;

import com.lms.app.dao.EntityManagerHelper;
import com.lms.app.dao.UsersDAO;
import com.lms.app.vo.Role;
import com.lms.app.vo.Users;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManageUsersAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strAction = request.getParameter("action");
    PrintWriter out = response.getWriter();
    response.setContentType("text/xml;charset=UTF-8");
    if ("read".equalsIgnoreCase(strAction))
    {
      getUsers(out);
    }
    else if ("edit".equalsIgnoreCase(strAction))
    {
      String id = request.getParameter("id");
      String userid = request.getParameter("userid");
      String password = request.getParameter("Password");
      String firstname = request.getParameter("FirstName");
      String lastname = request.getParameter("LastName");
      out.print(editUsers(id, userid, password, firstname, lastname));
    }
    else if ("delete".equalsIgnoreCase(strAction))
    {
      String id = request.getParameter("id");
      String userid = request.getParameter("userid");
      String password = request.getParameter("Password");
      String firstname = request.getParameter("FirstName");
      String lastname = request.getParameter("LastName");
      out.print(editUsers(id, userid, password, firstname, lastname));
    }
    return null;
  }
  
  public void getUsers(PrintWriter out)
  {
    UsersDAO userDao = new UsersDAO();
    List<Users> loginVos = userDao.findAll();
    if (loginVos.size() != 0)
    {
      out.print("<?xml version='1.0' encoding='utf-8'?>");
      out.print("<rows>");
      
      out.print("<page>1</page>");
      out.print("<total>1</total>");
      out.print("<records>" + loginVos.size() + "</records>");
      for (int i = 0; i < loginVos.size(); i++)
      {
        out.print("<row id='" + ((Users)loginVos.get(i)).getUserId() + "'>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getUserId() + "</cell>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getUsername() + "</cell>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getPassword() + "</cell>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getFname() + "</cell>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getLname() + "</cell>");
        out.print("<cell>" + ((Users)loginVos.get(i)).getRole().getRoleDesc() + "</cell>");
        out.print("</row>");
      }
      out.print("</rows>");
    }
  }
  
  public boolean editUsers(String id, String username, String password, String firstname, String lastname)
  {
    UsersDAO userDao = new UsersDAO();
    Users user = userDao.findById(new Long(id));
    try
    {
      user.setUsername(username);
      user.setPassword(password);
      user.setFname(firstname);
      user.setLname(lastname);
      EntityManagerHelper.beginTransaction();
      userDao.update(user);
      EntityManagerHelper.commit();
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
