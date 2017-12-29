package com.lms.app.action;

import com.lms.app.dao.BillingDAO;
import com.lms.app.dao.DAOFactory;
import com.lms.app.util.LMSConstants;
import com.lms.app.util.LMSUtility;
import com.lms.app.vo.BillDetailsVo;
import com.lms.app.vo.BillVo;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CreateAction
  extends Action
{
  private SimpleDateFormat sdftimeonly = new SimpleDateFormat("HH:mm:ss");
  private SimpleDateFormat sdfdateonly = new SimpleDateFormat("dd-MM-yyyy");
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String strDetailCount = request.getParameter("counter");
    String strCustomerName = request.getParameter("customername");
    String strBillDate = request.getParameter("billdate");
    System.out.println("Billdate from request" + strBillDate);
    String strAdvPayment = "".equalsIgnoreCase(request.getParameter("advpay")) ? "0.00" : request.getParameter("advpay");
    int total_cloth_count = 0;
    BillVo billvo = new BillVo();
    billvo.setServiceType(request.getParameter("servtype"));
    billvo.setCustomerName(strCustomerName);
    LMSConstants.getFiscalYear(this.sdfdateonly.parse(strBillDate));
    billvo.setBilldate(strBillDate);
    System.out.println("CreateAction-execute-" + billvo.getBilldate());
    billvo.setBillamount(Float.valueOf(Float.parseFloat(request.getParameter("totalamount"))));
    Calendar cltime = Calendar.getInstance();
    String strSysTime = this.sdftimeonly.format(cltime.getTime()).toString();
    System.out.println(strSysTime);
    billvo.setBillcrtime(strSysTime);
    billvo.setBillPaid("N");
    billvo.setBillDelivered("N");
    billvo.setSplcomments(request.getParameter("splcomments"));
    billvo.setHomedeli(request.getParameter("homedelival"));
    System.out.println(billvo.getHomedeli());
    HttpSession session = request.getSession();
    HashMap hshServiceShortDesc = (HashMap)session.getAttribute("serviceshortdesc");
    ArrayList arrbilldet = new ArrayList();
    for (int col = 0; col < Integer.parseInt(strDetailCount); col++)
    {
      BillDetailsVo billdet = new BillDetailsVo();
      billdet.setSerialno(Integer.parseInt(request.getParameter("srno" + col)));
      billdet.setClothid(Integer.parseInt(request.getParameter("cloth" + col)));
      billdet.setClothdesc(request.getParameter("clthidden" + col));
      billdet.setServiceid(Integer.parseInt(request.getParameter("service" + col)));
      billdet.setServicedesc(request.getParameter("srvhidden" + col));
      billdet.setServiceshortdesc((String)hshServiceShortDesc.get(new Integer(billdet.getServiceid()).toString()));
      billdet.setQuanity(Integer.parseInt(request.getParameter("quantity" + col)));
      total_cloth_count += billdet.getQuanity();
      billdet.setPrice(Float.parseFloat(request.getParameter("price" + col)));
      billdet.setTotalprice(Float.parseFloat(request.getParameter("totalprice" + col)));
      billdet.setItempaid("N");
      billdet.setItemdelivered("N");
      billdet.setComments(request.getParameter("comments" + col));
      arrbilldet.add(billdet);
    }
    billvo.setArrBillDetails(arrbilldet);
    System.out.print("Value of total_cloth_count-->" + total_cloth_count);
    billvo.setTotalCount(total_cloth_count);
    DAOFactory lmsFactory = DAOFactory.getDAOFactory(1);
    BillingDAO billingDAO = lmsFactory.getLMSBillingDAO();
    String billnumber = billingDAO.createBill(billvo);
    String strDate = LMSUtility.UIformatDate(LMSUtility.getCurrentDate());
    System.out.println("Total Bill-->" + billvo.getTotalCount());
    request.setAttribute("billdate", strDate);
    request.setAttribute("billn", billnumber);
    request.setAttribute("bill", billvo);
    if (billnumber != null) {
      return mapping.findForward("success");
    }
    return mapping.findForward("failure");
  }
}
