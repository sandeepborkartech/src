package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.util.LMSConstants;
import com.lms.app.util.LMSUtility;
import com.lms.app.vo.BillDetailsVo;
import com.lms.app.vo.BillVo;
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

public class UpdateAction
  extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strIsUpdateReq = request.getParameter("isUpdateReq");
    String strDetailCount = request.getParameter("counter");
    String strCustomerName = request.getParameter("customername");
    String strBillDate = request.getParameter("billdate");
    String setdeliverstatus = "N";
    int total_cloth_count = 0;
    String strBillnumber = request.getParameter("bill_number");
    String strPrintAction = request.getParameter("printaction");
    BillVo billvo = new BillVo();
    billvo.setServiceType(request.getParameter("servtype"));
    System.out.println("Value of servicetype in update action-->" + request.getParameter("servtype"));
    billvo.setCustomerName(strCustomerName);
    billvo.setBilldate(strBillDate);
    billvo.setBillamount(Float.valueOf(Float.parseFloat(request.getParameter("totalamount"))));
    billvo.setBillpaidamount(Float.valueOf(Float.parseFloat(request.getParameter("billamount"))));
    billvo.setBillnumber(strBillnumber);
    billvo.setSplcomments(request.getParameter("splcomments"));
    String strAdvPayment = request.getParameter("advpay");
    billvo.setBillcrtime(request.getParameter("billcrtime"));
    billvo.setHomedeli(request.getParameter("homedel"));
    if (billvo.getServiceType().equals(LMSConstants.DD)) {
      billvo.setAdvPay(Float.valueOf(Float.parseFloat(strAdvPayment)));
    }
    if (billvo.getBillamount().equals(billvo.getBillpaidamount()))
    {
      billvo.setBillPaid("Y");
      billvo.setBillPaidDate("'" + LMSUtility.DBformatDate(LMSUtility.getCurrentDate()) + "'");
    }
    else
    {
      billvo.setBillPaid("N");
      billvo.setBillPaidDate(null);
    }
    ArrayList arrbilldet = new ArrayList();
    HttpSession session = request.getSession();
    HashMap hshServiceShortDesc = (HashMap)session.getAttribute("serviceshortdesc");
    for (int col = 0; col < Integer.parseInt(strDetailCount); col++)
    {
      BillDetailsVo billdet = new BillDetailsVo();
      billdet.setSerialno(Integer.parseInt(request.getParameter("srno" + col)));
      billdet.setClothid(Integer.parseInt(request.getParameter("cloth" + col)));
      billdet.setClothdesc(request.getParameter("clothdesc" + col));
      billdet.setServiceid(Integer.parseInt(request.getParameter("service" + col)));
      billdet.setServicedesc(request.getParameter("servicedesc" + col));
      billdet.setServiceshortdesc((String)hshServiceShortDesc.get(new Integer(billdet.getServiceid()).toString()));
      billdet.setQuanity(Integer.parseInt(request.getParameter("quantity" + col)));
      total_cloth_count += billdet.getQuanity();
      billdet.setPrice(Float.parseFloat(request.getParameter("price" + col)));
      billdet.setTotalprice(Float.parseFloat(request.getParameter("totalprice" + col)));
      billdet.setComments(request.getParameter("comments" + col));
      billdet.setItempaid("Y".equalsIgnoreCase(request.getParameter("hiddenpaid" + col)) ? "Y" : "N");
      billdet.setItemdelivered("Y".equalsIgnoreCase(request.getParameter("hiddendeliver" + col)) ? "Y" : "N");
      if ("Y".equalsIgnoreCase(billdet.getItemdelivered())) {
        setdeliverstatus = "Y";
      } else {
        setdeliverstatus = "N";
      }
      arrbilldet.add(billdet);
    }
    billvo.setArrBillDetails(arrbilldet);
    billvo.setTotalCount(total_cloth_count);
    System.out.println("UpdateAction- execute-setdeliverstatus value - " + setdeliverstatus);
    billvo.setBillDelivered(setdeliverstatus);
    if ("Y".equalsIgnoreCase(billvo.getBillDelivered()))
    {
      String billDelDate = "'" + LMSUtility.DBformatDate(LMSUtility.getCurrentDate()) + "'";
      billvo.setBillDeliveredDate(billDelDate);
    }
    System.out.println("UpdateAction- execute-" + billvo.getBillDelivered());
    if ("Y".equalsIgnoreCase(strIsUpdateReq))
    {
      DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
      BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
      String billingsuccess = billingDAO.updateBill(billvo);
      if (billingsuccess != null)
      {
        if ("yes".equalsIgnoreCase(strPrintAction))
        {
          request.setAttribute("bill", billvo);
          return mapping.findForward("successprint");
        }
        return mapping.findForward("success");
      }
      return mapping.findForward("failure");
    }
    request.setAttribute("bill", billvo);
    return mapping.findForward("successprint");
  }
}
