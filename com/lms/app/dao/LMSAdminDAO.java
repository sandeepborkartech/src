package com.lms.app.dao;

import com.lms.app.util.LMSUtility;
import com.lms.app.vo.BillVo;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class LMSAdminDAO
  implements AdminDAO
{
  private String strUpdateBillMaster = "Update billmaster set bill_paid='Y', bill_paid_date=?, bill_delivered='Y', bill_delivered_date=?,bill_paid_amount=? where billnum=?";
  private String strUpdateBillDetails = "Update billdetails set item_paid='Y', item_paid_date=?, item_delivered='Y', item_delivered_date=? where billnum=?";
  
  public String updateBulkBillDate(ArrayList<BillVo> arrBills)
    throws Exception
  {
    StringBuffer strErrorMessage = new StringBuffer();
    strErrorMessage.append("Update failed for bill number(s)");
    String strSuccessMessage = "Update Successfull";
    int failureCount = 0;
    for (BillVo bill : arrBills)
    {
      boolean blnmasterUpdate = updateMasterBillDate(bill);
      if (!blnmasterUpdate)
      {
        failureCount++;
        strErrorMessage.append(" ");
        strErrorMessage.append(bill.getBillnumber());
      }
    }
    if (failureCount > 0) {
      return strErrorMessage.toString();
    }
    return strSuccessMessage;
  }
  
  private boolean updateMasterBillDate(BillVo bill)
  {
    Connection conn = null;
    PreparedStatement pstm = null;
    PreparedStatement pstd = null;
    try
    {
      conn = LMSDAOFactory.createConnection();
      conn.setAutoCommit(false);
      pstm = conn.prepareStatement(this.strUpdateBillMaster);
      pstd = conn.prepareStatement(this.strUpdateBillDetails);
      pstm.setString(1, LMSUtility.DBformatDate(bill.getBillPaidDate()));
      pstm.setString(2, LMSUtility.DBformatDate(bill.getBillDeliveredDate()));
      pstm.setFloat(3, bill.getBillpaidamount().floatValue());
      pstm.setString(4, bill.getBillnumber().trim());
      int upm = pstm.executeUpdate();
      if (upm < 1) {
        return false;
      }
     // int upm;
      pstd.setString(1, LMSUtility.DBformatDate(bill.getBillPaidDate()));
      pstd.setString(2, LMSUtility.DBformatDate(bill.getBillDeliveredDate()));
      pstd.setString(3, bill.getBillnumber());
      int upd = pstd.executeUpdate();
      if (upd < 1) {
        return false;
      }
      //int upd;
      //int upm;
      conn.commit();
      return true;
    }
    catch (Exception e)
    {
      System.out.println("Error while updating bill paid date" + e.getMessage());
      return false;
    }
    finally
    {
      try
      {
        if (pstm != null) {
          pstm.close();
        }
        if (pstd != null) {
          pstd.close();
        }
        if (conn != null) {
          conn.close();
        }
      }
      catch (Exception e)
      {
        System.out.println("Error while closing connections");
      }
    }
  }
}
