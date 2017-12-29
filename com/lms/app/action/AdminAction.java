package com.lms.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strAdminaction = request.getParameter("actioncalled");
    char[] chAdmin = strAdminaction.toCharArray();
    switch (chAdmin[0])
    {
    case 'b': 
      return mapping.findForward("multibill");
    case 'p': 
      return mapping.findForward("pass");
    case 'u': 
      return mapping.findForward("users");
    case 'm': 
      return mapping.findForward("masters");
    }
    return null;
  }
}
