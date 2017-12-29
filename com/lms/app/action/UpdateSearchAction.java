package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.vo.BillVo;
import com.lms.app.vo.UpdateSearchVo;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UpdateSearchAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    UpdateSearchVo upd = new UpdateSearchVo();
    String strBillNUmber = request.getParameter("billnum");
    upd.setBillnumber(strBillNUmber);
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    BillVo billvo = billingDAO.searchBillByBillNumber(upd);
    System.out.println("UpdateSearchAction-execute- getdate->" + billvo.getBilldate());
    request.setAttribute("billvo", billvo);
    return mapping.findForward("success");
  }
}
