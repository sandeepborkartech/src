package com.lms.app.action;

import com.lms.app.dao.AdminDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.vo.BillVo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminUpdateBills
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strTotalCount = request.getParameter("totalcount");
    ArrayList<BillVo> arrBills = new ArrayList();
    int count = Integer.parseInt(strTotalCount);
    for (int i = 1; i < count; i++)
    {
      String strChkValue = request.getParameter("updatechk" + i);
      if (strChkValue != null)
      {
        String strPaidDate = request.getParameter("paiddate" + i);
        String strPaidAmount = request.getParameter("billpaid" + i);
        float flamt = new Float(strPaidAmount).floatValue();
        BillVo billvo = new BillVo();
        billvo.setBillnumber(strChkValue);
        billvo.setBillPaidDate(strPaidDate);
        billvo.setBillPaid("Y");
        billvo.setBillDelivered("Y");
        billvo.setBillDeliveredDate(strPaidDate);
        billvo.setBillpaidamount(Float.valueOf(flamt));
        arrBills.add(billvo);
      }
    }
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    AdminDAO adminDAO = lmsFactory.getLMSAdminDAO();
    String strMessage = adminDAO.updateBulkBillDate(arrBills);
    HttpSession session = request.getSession();
    session.setAttribute("UpdateMessage", strMessage);
    if ("Update Successful".equalsIgnoreCase(strMessage)) {
      return mapping.findForward("success");
    }
    return mapping.findForward("failure");
  }
}
