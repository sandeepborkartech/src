package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.vo.ReportCriteriaVo;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReportAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
    session.removeAttribute("searchresults");
    System.out.println("Inside reports execute method");
    String strfrombilldate = request.getParameter("frombilldate");
    System.out.println("Value of frombilldate-->" + strfrombilldate);
    String strtobilldate = request.getParameter("tobilldate");
    System.out.println("Value of tobilldate-->" + strtobilldate);
    String strfrompaiddate = request.getParameter("frompaiddate");
    System.out.println("Value of frompaiddate-->" + strfrompaiddate);
    String strtopaiddate = request.getParameter("topaiddate");
    System.out.println("Value of topaiddate-->" + strtopaiddate);
    String strCustomerName = request.getParameter("customername");
    System.out.println("Value of strCustomerName-->" + strCustomerName);
    String strpaystatus = request.getParameter("paystatus");
    System.out.println("Value of strpaystatus-->" + strpaystatus);
    String strservicetype = request.getParameter("servicetype");
    System.out.println("Value of servicetype-->" + strservicetype);
    ReportCriteriaVo reportvo = new ReportCriteriaVo();
    reportvo.setFrombilldate(strfrombilldate);
    reportvo.setTobilldate(strtobilldate);
    reportvo.setFrompaiddate(strfrompaiddate);
    reportvo.setTopaiddate(strtopaiddate);
    reportvo.setCustomername(strCustomerName);
    reportvo.setPaystatus(strpaystatus);
    reportvo.setServicetype(strservicetype);
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    ArrayList searchResults = billingDAO.generateReport(reportvo);
    if (searchResults != null)
    {
      request.setAttribute("searchresults", searchResults);
      return mapping.findForward("success");
    }
    return mapping.findForward("failure");
  }
}
