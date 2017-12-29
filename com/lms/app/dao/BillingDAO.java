package com.lms.app.dao;

import com.lms.app.vo.AdminUpdateMultiBillsCriteriaVo;
import com.lms.app.vo.BillVo;
import com.lms.app.vo.ReportCriteriaVo;
import com.lms.app.vo.SearchParamVo;
import com.lms.app.vo.UpdateSearchVo;
import java.util.ArrayList;

public abstract interface BillingDAO
{
  public abstract String createBill(BillVo paramBillVo)
    throws Exception;
  
  public abstract String updateBill(BillVo paramBillVo)
    throws Exception;
  
  public abstract BillVo searchBillByBillNumber(UpdateSearchVo paramUpdateSearchVo)
    throws Exception;
  
  public abstract ArrayList searchBills(SearchParamVo paramSearchParamVo)
    throws Exception;
  
  public abstract ArrayList generateReport(ReportCriteriaVo paramReportCriteriaVo)
    throws Exception;
  
  public abstract ArrayList getUpdateMultiBillsList(AdminUpdateMultiBillsCriteriaVo paramAdminUpdateMultiBillsCriteriaVo)
    throws Exception;
  
  public abstract String getBillDate()
    throws Exception;
}
