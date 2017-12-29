package com.lms.app.action;

import com.lms.app.form.LinkForm;
import com.lms.app.util.LMSUtility;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LinkAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LinkForm linkForm = (LinkForm)form;
    HttpSession session = request.getSession();
    session.removeAttribute("searchresults");
    String strDate = LMSUtility.getCurrentDate();
    System.out.println("Date retrieved in LinkAction-->" + strDate);
    request.setAttribute("billdate", strDate);
    System.out.println("linkForm.getActioncalled()-->" + linkForm.getActioncalled());
    switch (linkForm.getActioncalled())
    {
    case 'c': 
      return mapping.findForward("create");
    case 'u': 
      return mapping.findForward("update");
    case 's': 
      return mapping.findForward("search");
    case 'm': 
      return mapping.findForward("main");
    case 'r': 
      return mapping.findForward("report");
    case 'a': 
      return mapping.findForward("admin_update_bill");
    }
    return null;
  }
}
