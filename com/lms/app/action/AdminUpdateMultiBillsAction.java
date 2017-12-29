package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.vo.AdminUpdateMultiBillsCriteriaVo;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminUpdateMultiBillsAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    AdminUpdateMultiBillsCriteriaVo critvo = new AdminUpdateMultiBillsCriteriaVo();
    String strfrombilldate = request.getParameter("frombilldate");
    critvo.setFrombilldate(strfrombilldate);
    String strtobilldate = request.getParameter("tobilldate");
    critvo.setTobilldate(strtobilldate);
    String strfrombillnum = request.getParameter("frombillnum");
    critvo.setFrombillnum(strfrombillnum);
    String strtobillnum = request.getParameter("tobillnum");
    critvo.setTobillnum(strtobillnum);
    String strpaystatus = request.getParameter("paystatus");
    critvo.setPaystatus(strpaystatus);
    String strBilllist = request.getParameter("billlist");
    String[] listofbills = strBilllist.split(",");
    
    String finalbilllist = "";
    for (int i = 0; i < listofbills.length; i++) {
      if (listofbills[i] != "") {
        if (!"".equalsIgnoreCase(finalbilllist)) {
          finalbilllist = finalbilllist + ",'" + listofbills[i] + "'";
        } else {
          finalbilllist = "'" + listofbills[i] + "'";
        }
      }
    }
    critvo.setBilllist(finalbilllist);
    System.out.println(finalbilllist);
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    ArrayList searchResults = billingDAO.getUpdateMultiBillsList(critvo);
    if (searchResults != null)
    {
      request.setAttribute("searchresults", searchResults);
      return mapping.findForward("success");
    }
    return mapping.findForward("failure");
  }
}
