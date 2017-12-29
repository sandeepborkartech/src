package com.lms.app.dao;

import com.lms.app.util.LMSConstants;
import com.lms.app.util.LMSUtility;
import com.lms.app.vo.AdminUpdateMultiBillsCriteriaVo;
import com.lms.app.vo.BillDetailsVo;
import com.lms.app.vo.BillVo;
import com.lms.app.vo.ReportCriteriaVo;
import com.lms.app.vo.ReportResultsVo;
import com.lms.app.vo.SearchParamVo;
import com.lms.app.vo.SearchResultsVo;
import com.lms.app.vo.UpdateSearchVo;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class LMSBillingDAO
  implements BillingDAO
{
  String strGetSequence;
  String strUpdate_SearchMasterQuery;
  String strUpdate_SearchDetailQuery;
  String strSearch;
  String strReport;
  String strReport_part2;
  
  public LMSBillingDAO()
  {
    this.strGetSequence = "Select getsequence(?,?)";
    this.strUpdate_SearchMasterQuery = "Select billnum,servcatid,cust_name,bill_date,bill_amount,bill_paid,bill_paid_date,bill_delivered,bill_delivered_date,bill_paid_amount,splcomments,bill_advance_paid,bill_clothes_count from BillMaster where billnum=?";
    
    this.strUpdate_SearchDetailQuery = "Select bd.item_id,bd.billnum,bd.clothid,bd.serviceid,bd.item_count,bd.price,bd.total_price,bd.item_paid,bd.item_paid_date,bd.item_delivered,bd.item_delivered_date,bd.comments,cm.cloth_desc, sm.service_desc from BillDetails bd,ClothMaster cm, ServiceMaster sm where billnum=? and bd.clothid=cm.clothid and bd.serviceid=sm.serviceid";
    
    this.strSearch = "Select billnum,cust_name,bill_date,bill_amount,bill_paid_amount,bill_paid_date from BillMaster where ";
    
    this.strReport = "Select bill_date,BillMaster.billnum,cust_name,sum(item_count),bill_amount,bill_paid_amount, bill_paid_date from BillMaster,BillDetails,ClothMaster where ";
    
    this.strReport_part2 = " and BillMaster.billnum = BillDetails.billnum and BillDetails.clothid = ClothMaster.clothid group by BillMaster.billnum";
  }
  
  public String createBill(BillVo billvo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    PreparedStatement pstm = conn.prepareStatement(this.strGetSequence);
    System.out.println("billvo.getServiceType()-->" + billvo.getServiceType());
    if (billvo.getServiceType().equals(LMSConstants.WASH)) {
      pstm.setString(1, LMSConstants.WASH_SEQ);
    } else if (billvo.getServiceType().equals(LMSConstants.IRON)) {
      pstm.setString(1, LMSConstants.IRON_SEQ);
    } else if (billvo.getServiceType().equals(LMSConstants.DD)) {
      pstm.setString(1, LMSConstants.DD_SEQ);
    }
    pstm.setString(2, LMSConstants.fiscalyear);
    ResultSet rst = pstm.executeQuery();
    int lmssequence = 0;
    for (lmssequence = 0; rst.next(); lmssequence = rst.getInt(1)) {}
    rst.close();
    pstm.close();
    String billnum = LMSUtility.getCreateBillNumber(lmssequence, billvo.getServiceType()) + String.format("%06d", new Object[] {
      Integer.valueOf(lmssequence) });
    
    billvo.setBillnumber(billnum);
    boolean masterStatus = insertBillMaster(conn, billvo);
    if (!masterStatus)
    {
      conn.rollback();
      conn.close();
      return null;
    }
    boolean childStatus = insertChildDetails(conn, billvo);
    if (!childStatus)
    {
      conn.rollback();
      conn.close();
      return null;
    }
    conn.commit();
    conn.close();
    return billvo.getBillnumber();
  }
  
  public boolean insertBillMaster(Connection conn, BillVo billvo)
    throws Exception
  {
    String str_bill_paid_date = null;
    String str_bill_paid = "N";
    String str_bill_delivered = "N";
    String str_bill_delivered_date = null;
    int int_clothes_cnt = 0;
    String str_bill_date = null;
    System.out.println("DAO - insertBillMaster-->" + billvo.getAdvPay());
    str_bill_paid = billvo.getBillPaid();
    System.out.println("str_bill_paid--dao-" + str_bill_paid);
    str_bill_paid_date = billvo.getBillPaidDate();
    System.out.println("str_bill_paid_date--dao-" + str_bill_paid_date);
    str_bill_delivered = billvo.getBillDelivered();
    System.out.println("str_bill_delivered--dao-" + str_bill_delivered);
    str_bill_delivered_date = billvo.getBillDeliveredDate();
    System.out.println("str_bill_delivered_date--dao-" + str_bill_delivered_date);
    int_clothes_cnt = billvo.getTotalCount();
    str_bill_date = LMSUtility.DBformatDate(billvo.getBilldate());
    String strCreateBillMaster = "Insert into BillMaster values('" + billvo.getBillnumber() + "','" + billvo.getServiceType() + "','" + billvo.getCustomerName() + "'" + ",'" + str_bill_date + "'," + billvo.getBillamount() + ",'" + str_bill_paid + "'," + str_bill_paid_date + ",'" + str_bill_delivered + "'," + str_bill_delivered_date + "," + billvo.getBillpaidamount() + ",'" + billvo.getSplcomments() + "'," + billvo.getAdvPay() + "," + int_clothes_cnt + ",'" + billvo.getBillcrtime() + "'," + "null," + "'" + billvo.getHomedeli() + "')" + " ON DUPLICATE KEY UPDATE CUST_NAME='" + billvo.getCustomerName() + "',BILL_DATE='" + str_bill_date + "',BILL_PAID_DATE=" + str_bill_paid_date + ",BILL_AMOUNT=" + billvo.getBillamount() + ",BILL_PAID='" + str_bill_paid + "',BILL_PAID_AMOUNT=" + billvo.getBillpaidamount() + ",BILL_DELIVERED='" + str_bill_delivered + "',BILL_DELIVERED_DATE=" + str_bill_delivered_date + ",SPLCOMMENTS='" + billvo.getSplcomments() + "',BILL_ADVANCE_PAID=" + billvo.getAdvPay() + ",BILL_CLOTHES_COUNT=" + int_clothes_cnt;
    System.out.println("DAO - insertBillMaster- Master Query=>" + strCreateBillMaster);
    Statement stm = conn.createStatement();
    int intRow = stm.executeUpdate(strCreateBillMaster);
    boolean insertStatus = intRow > 0;
    stm.close();
    return insertStatus;
  }
  
  public boolean insertChildDetails(Connection conn, BillVo billvo)
    throws Exception
  {
    boolean insertStatus = true;
    Statement stmt = null;
    String strPaidDate = null;
    String strDeliveredDate = null;
    ArrayList arrBillDetails = billvo.getArrBillDetails();
    for (Iterator iterator = arrBillDetails.iterator(); iterator.hasNext();)
    {
      BillDetailsVo bd = (BillDetailsVo)iterator.next();
      System.out.println("ItemPaid-->" + bd.getItemPaidDate());
      if ("Y".equalsIgnoreCase(bd.getItempaid()))
      {
        strPaidDate = "'" + LMSUtility.DBformatDate(LMSUtility.getCurrentDate()) + "'";
        System.out.println("DAO-strPaidDate-" + strPaidDate);
      }
      if ("Y".equalsIgnoreCase(bd.getItemdelivered()))
      {
        strDeliveredDate = "'" + LMSUtility.DBformatDate(LMSUtility.getCurrentDate()) + "'";
        System.out.println("DAO-strDeliveredDate" + strDeliveredDate);
      }
      String strCreateBillDetails = "insert into BillDetails values(" + bd.getSerialno() + ",'" + billvo.getBillnumber() + "'" + "," + bd.getClothid() + "," + bd.getServiceid() + "," + bd.getQuanity() + "," + bd.getPrice() + "," + bd.getTotalprice() + ",'" + bd.getItempaid() + "'," + bd.getItemPaidDate() + ",'" + bd.getItemdelivered() + "'," + bd.getItemDeliveredDate() + ",'" + bd.getComments() + "')" + " on duplicate key update clothid=" + bd.getClothid() + ",serviceid=" + bd.getServiceid() + "," + "item_count=" + bd.getQuanity() + ",price=" + bd.getPrice() + ",total_price=" + bd.getTotalprice() + ",item_paid_date=" + strPaidDate + ",item_delivered_date=" + strDeliveredDate + ",comments='" + bd.getComments() + "'" + ",item_paid='" + bd.getItempaid() + "',item_delivered='" + bd.getItemdelivered() + "'";
      stmt = conn.createStatement();
      System.out.println("Child table query-->" + strCreateBillDetails);
      int intRow = stmt.executeUpdate(strCreateBillDetails);
      insertStatus = intRow > 0;
      if (!insertStatus)
      {
        stmt.close();
        stmt = null;
        return insertStatus;
      }
    }
    stmt.close();
    stmt = null;
    return insertStatus;
  }
  
  public BillVo searchBillByBillNumber(UpdateSearchVo updvo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    PreparedStatement pstm = conn.prepareStatement(this.strUpdate_SearchMasterQuery);
    pstm.setString(1, updvo.getBillnumber());
    System.out.println("strUpdate_SearchMasterQuery--->" + this.strUpdate_SearchMasterQuery);
    ResultSet rst = pstm.executeQuery();
    BillVo billvo = new BillVo();
    ArrayList arrBillDetails = new ArrayList();
    for (; rst.next(); billvo.setTotalCount(rst.getInt(13)))
    {
      billvo.setBillnumber(rst.getString(1));
      billvo.setServiceType(rst.getString(2));
      billvo.setCustomerName(rst.getString(3));
      String str = rst.getString(4);
      System.out.println("date--->" + str);
      billvo.setBilldate(LMSUtility.UIformatDate(str));
      System.out.println("date in bean object--->" + billvo.getBilldate());
      billvo.setBillamount(Float.valueOf(rst.getFloat(5)));
      billvo.setBillPaid(rst.getString(6));
      billvo.setBillPaidDate(LMSUtility.UIformatDate(rst.getString(7)));
      billvo.setBillDelivered(rst.getString(8));
      billvo.setBillDeliveredDate(LMSUtility.UIformatDate(rst.getString(9)));
      billvo.setBillpaidamount(Float.valueOf(rst.getFloat(10)));
      billvo.setSplcomments(rst.getString(11));
      billvo.setAdvPay(Float.valueOf(rst.getFloat(12)));
    }
    rst.close();
    pstm.close();
    pstm = conn.prepareStatement(this.strUpdate_SearchDetailQuery);
    pstm.setString(1, updvo.getBillnumber());
    System.out.println("strUpdate_SearchDetailQuery--->" + this.strUpdate_SearchDetailQuery);
    BillDetailsVo bd;
    for (rst = pstm.executeQuery(); rst.next(); arrBillDetails.add(bd))
    {
      bd = new BillDetailsVo();
      bd.setSerialno(rst.getInt(1));
      bd.setClothid(rst.getInt(3));
      bd.setServiceid(rst.getInt(4));
      bd.setQuanity(rst.getInt(5));
      bd.setPrice(rst.getFloat(6));
      bd.setTotalprice(rst.getFloat(7));
      bd.setItempaid(rst.getString(8));
      bd.setItemdelivered(rst.getString(10));
      bd.setComments(rst.getString(12));
      bd.setClothdesc(rst.getString(13));
      bd.setServicedesc(rst.getString(14));
    }
    billvo.setArrBillDetails(arrBillDetails);
    rst.close();
    pstm.close();
    conn.close();
    return billvo;
  }
  
  public String updateBill(BillVo billvo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    
    boolean masterStatus = insertBillMaster(conn, billvo);
    if (!masterStatus)
    {
      conn.rollback();
      conn.close();
      return "failure";
    }
    boolean childStatus = insertChildDetails(conn, billvo);
    if (!childStatus)
    {
      conn.rollback();
      conn.close();
      return "failure";
    }
    conn.commit();
    conn.close();
    return "Success";
  }
  
  public ArrayList searchBills(SearchParamVo searchVo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    String searchcriteria = "";
    ArrayList srrResults = new ArrayList();
    if (!"".equalsIgnoreCase(searchVo.getBilldate())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_date='" + searchVo.getBilldate() + "'";
      } else {
        searchcriteria = " bill_date='" + searchVo.getBilldate() + "'";
      }
    }
    if (!"".equalsIgnoreCase(searchVo.getHomedeli())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and homedeli='" + searchVo.getHomedeli() + "'";
      } else {
        searchcriteria = " homedeli='" + searchVo.getHomedeli() + "'";
      }
    }
    if (!"".equalsIgnoreCase(searchVo.getCustomername())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and cust_name like('%" + searchVo.getCustomername() + "%')";
      } else {
        searchcriteria = " cust_name like('%" + searchVo.getCustomername() + "%')";
      }
    }
    if (!"".equalsIgnoreCase(searchVo.getBillnumber())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and billnum='" + searchVo.getBillnumber() + "'";
      } else {
        searchcriteria = " billnum='" + searchVo.getBillnumber() + "'";
      }
    }
    this.strSearch += searchcriteria;
    
    Statement stmt = conn.createStatement();
    SearchResultsVo sresultsvo;
    ResultSet rst = null;
    for (rst = stmt.executeQuery(this.strSearch); rst.next(); srrResults.add(sresultsvo))
    {
      sresultsvo = new SearchResultsVo();
      sresultsvo.setBillnumber(rst.getString(1));
      sresultsvo.setCustomername(rst.getString(2));
      sresultsvo.setBilldate(LMSUtility.UIformatDate(rst.getString(3)));
      sresultsvo.setBillamount(rst.getFloat(4));
      sresultsvo.setBillpaidamount(rst.getFloat(5));
      sresultsvo.setBillPaidDate(LMSUtility.UIformatDate(rst.getString(6)));
    }
    rst.close();
    stmt.close();
    conn.close();
    return srrResults;
  }
  
  public ArrayList getUpdateMultiBillsList(AdminUpdateMultiBillsCriteriaVo adminVo)
    throws Exception
  {
    ArrayList srrResults = new ArrayList();
    System.out.println("Inside getUpdateMultiBillsList-->");
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    String searchcriteria = "";
    if ((!"".equalsIgnoreCase(adminVo.getFrombilldate())) && (!"".equalsIgnoreCase(adminVo.getTobilldate()))) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_date between'" + adminVo.getFrombilldate() + "' AND '" + adminVo.getTobilldate() + "'";
      } else {
        searchcriteria = " bill_date between'" + adminVo.getFrombilldate() + "' AND '" + adminVo.getTobilldate() + "'";
      }
    }
    if ((!"".equalsIgnoreCase(adminVo.getFrombillnum())) && (!"".equalsIgnoreCase(adminVo.getTobillnum())))
    {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and BillMaster.billnum between'" + adminVo.getFrombillnum() + "' AND '" + adminVo.getTobillnum() + "'";
      } else {
        searchcriteria = " BillMaster.billnum between'" + adminVo.getFrombillnum() + "' AND '" + adminVo.getTobillnum() + "'";
      }
    }
    else if (!"".equalsIgnoreCase(adminVo.getBilllist())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and BillMaster.billnum  in (" + adminVo.getBilllist() + ")";
      } else {
        searchcriteria = " BillMaster.billnum  in (" + adminVo.getBilllist() + ")";
      }
    }
    if (!"".equalsIgnoreCase(adminVo.getPaystatus())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_paid='" + adminVo.getPaystatus() + "'";
      } else {
        searchcriteria = " bill_paid='" + adminVo.getPaystatus() + "'";
      }
    }
    this.strReport = (this.strReport + searchcriteria + this.strReport_part2);
    System.out.println("query to be executed-->" + this.strReport);
    Statement stmt = conn.createStatement();
    ReportResultsVo reportresultsvo;
    ResultSet rst = null;
    for (rst = stmt.executeQuery(this.strReport); rst.next(); srrResults.add(reportresultsvo))
    {
      reportresultsvo = new ReportResultsVo();
      reportresultsvo.setBilldate(LMSUtility.UIformatDate(rst.getString(1)));
      reportresultsvo.setBillnumber(rst.getString(2));
      reportresultsvo.setCustomername(rst.getString(3));
      reportresultsvo.setBilldesc(getReportDesc(reportresultsvo.getBillnumber()));
      reportresultsvo.setTotalCount(rst.getInt(4));
      reportresultsvo.setBillamount(rst.getFloat(5));
      reportresultsvo.setBillpaidamount(rst.getFloat(6));
      reportresultsvo.setBillPaidDate(LMSUtility.UIformatDate(rst.getString(7)));
    }
    rst.close();
    stmt.close();
    conn.close();
    return srrResults;
  }
  
  public ArrayList generateReport(ReportCriteriaVo reportVo)
    throws Exception
  {
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    String searchcriteria = "";
    ArrayList srrResults = new ArrayList();
    if ((!"".equalsIgnoreCase(reportVo.getFrombilldate())) && (!"".equalsIgnoreCase(reportVo.getTobilldate()))) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_date between'" + reportVo.getFrombilldate() + "' AND '" + reportVo.getTobilldate() + "'";
      } else {
        searchcriteria = " bill_date between'" + reportVo.getFrombilldate() + "' AND '" + reportVo.getTobilldate() + "'";
      }
    }
    if ((!"".equalsIgnoreCase(reportVo.getFrompaiddate())) && (!"".equalsIgnoreCase(reportVo.getTopaiddate()))) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_paid_date between'" + reportVo.getFrompaiddate() + "' AND '" + reportVo.getTopaiddate() + "'";
      } else {
        searchcriteria = " bill_paid_date between'" + reportVo.getFrompaiddate() + "' AND '" + reportVo.getTopaiddate() + "'";
      }
    }
    if (!"".equalsIgnoreCase(reportVo.getCustomername())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and cust_name like('%" + reportVo.getCustomername() + "%')";
      } else {
        searchcriteria = " cust_name like('%" + reportVo.getCustomername() + "%')";
      }
    }
    if (!"".equalsIgnoreCase(reportVo.getPaystatus())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and bill_paid='" + reportVo.getPaystatus() + "'";
      } else {
        searchcriteria = " bill_paid='" + reportVo.getPaystatus() + "'";
      }
    }
    if (!"".equalsIgnoreCase(reportVo.getServicetype())) {
      if (!"".equals(searchcriteria)) {
        searchcriteria = searchcriteria + " and servcatid='" + reportVo.getServicetype() + "'";
      } else {
        searchcriteria = " servcatid='" + reportVo.getServicetype() + "'";
      }
    }
    this.strReport = (this.strReport + searchcriteria + this.strReport_part2);
    System.out.println("query to be executed-->" + this.strReport);
    Statement stmt = conn.createStatement();
    ReportResultsVo reportresultsvo;
    ResultSet rst = null;
    for (rst = stmt.executeQuery(this.strReport); rst.next(); srrResults.add(reportresultsvo))
    {
      reportresultsvo = new ReportResultsVo();
      reportresultsvo.setBilldate(LMSUtility.UIformatDate(rst.getString(1)));
      reportresultsvo.setBillnumber(rst.getString(2));
      reportresultsvo.setCustomername(rst.getString(3));
      reportresultsvo.setBilldesc(getReportDesc(reportresultsvo.getBillnumber()));
      reportresultsvo.setTotalCount(rst.getInt(4));
      reportresultsvo.setBillamount(rst.getFloat(5));
      reportresultsvo.setBillpaidamount(rst.getFloat(6));
      reportresultsvo.setBillPaidDate(LMSUtility.UIformatDate(rst.getString(7)));
    }
    rst.close();
    stmt.close();
    conn.close();
    return srrResults;
  }
  
  public String getReportDesc(String billnum)
    throws Exception
  {
    String repDesc = "";
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    String repDescQuery = "";
    System.out.println("query to be executed-->" + this.strReport);
    repDescQuery = "Select CONCAT(item_count,':',cloth_desc) from BillMaster,BillDetails,ClothMaster where BillMaster.billnum='" + 
    
      billnum + "' and BillMaster.billnum = BillDetails.billnum and BillDetails.clothid = ClothMaster.clothid";
    
    Statement stmt = conn.createStatement();
    System.out.println("Query for Desc -->" + repDescQuery);
    ResultSet rst;
    for ( rst = stmt.executeQuery(repDescQuery); rst.next();) {
      if (repDesc == "")
      {
        System.out.println("Inside if");
        repDesc = rst.getString(1);
      }
      else
      {
        System.out.println("Inside else");
        repDesc = repDesc + " ; " + rst.getString(1);
      }
    }
    rst.close();
    stmt.close();
    conn.close();
    System.out.println("Desc collected --" + repDesc);
    return repDesc;
  }
  
  public String getBillDate()
    throws Exception
  {
    String billDate = null;
    Connection conn = LMSDAOFactory.createConnection();
    conn.setAutoCommit(false);
    String getBillDateQuery = "";
    getBillDateQuery = "select if(date_format(curdate(),'%w')= 1,date_format(curdate()+1,'%Y-%m-%d'),curdate())";
    
    Statement stmt = conn.createStatement();
    System.out.println("Query for billdate -->" + getBillDateQuery);
    ResultSet rst;
    for (rst = stmt.executeQuery(getBillDateQuery); rst.next();)
    {
      System.out.println("Inside else");
      billDate = rst.getString(1);
      billDate = LMSUtility.UIformatDate(billDate);
    }
    System.out.println("DAO- getBilldate- Date collected --" + billDate);
    rst.close();
    stmt.close();
    conn.close();
    return billDate;
  }
}
