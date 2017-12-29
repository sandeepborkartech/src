package com.lms.app.action;

import com.lms.app.dao.DAOFactory;
import com.lms.app.dao.LoginDAO;
import com.lms.app.form.LoginForm;
import com.lms.app.vo.LoginVo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LoginForm loginForm = (LoginForm)form;
    LoginVo loginvo = new LoginVo();
    ActionMessages messages = new ActionMessages();
    loginvo.setUsername(loginForm.getUserName());
    loginvo.setPassword(loginForm.getPassword());
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    LoginDAO loginDAO = lmsFactory.getLMSLoginDAO();
    LoginVo loginvoreturn = loginDAO.login(loginvo);
    if ("success".equalsIgnoreCase(loginvoreturn.getLoginsuccess()))
    {
      HttpSession session = request.getSession();
      
      ArrayList arrMasterData = loginDAO.getLMSMasterData();
      String[] strLMSMasterData = (String[])arrMasterData.get(0);
      HashMap hshServMasterData = (HashMap)arrMasterData.get(1);
      session.setAttribute("user", loginvoreturn);
      session.setAttribute("serviceshortdesc", hshServMasterData);
      if (strLMSMasterData[0] != null) {
        session.setAttribute("clothmasterlist", strLMSMasterData[0]);
      } else {
        System.out.println("Error in getting cloth list");
      }
      if (strLMSMasterData[1] != null) {
        session.setAttribute("servicemasterlist", strLMSMasterData[1]);
      } else {
        System.out.println("Error in getting service list");
      }
      if (strLMSMasterData[1] != null) {
        session.setAttribute("servicetypemasterlist", strLMSMasterData[2]);
      } else {
        System.out.println("Error in getting service type list");
      }
    }
    else
    {
      messages.add("errors", new ActionMessage("error.login.invalid"));
      saveMessages(request, messages);
    }
    System.out.println(loginvoreturn.getLoginsuccess());
    return mapping.findForward(loginvoreturn.getLoginsuccess());
  }
}
