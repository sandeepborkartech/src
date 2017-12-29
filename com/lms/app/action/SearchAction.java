package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.util.LMSUtility;
import com.lms.app.vo.SearchParamVo;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strBillNumber = request.getParameter("billnum");
    String strCustomerName = request.getParameter("customername");
    String strBillDate = request.getParameter("billdate");
    String strHomedeli = request.getParameter("homedeli");
    
    SearchParamVo searchvo = new SearchParamVo();
    if (strBillDate == "")
    {
      System.out.println("Do nothing" + searchvo.getBilldate());
    }
    else
    {
      searchvo.setBilldate(LMSUtility.DBformatDate(strBillDate));
      System.out.println("Change date to DB format" + searchvo.getBilldate());
    }
    searchvo.setCustomername(strCustomerName);
    searchvo.setBillnumber(strBillNumber);
    searchvo.setHomedeli(strHomedeli);
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    ArrayList searchResults = billingDAO.searchBills(searchvo);
    if (searchResults != null)
    {
      request.setAttribute("searchresults", searchResults);
      return mapping.findForward("success");
    }
    return mapping.findForward("failure");
  }
}
