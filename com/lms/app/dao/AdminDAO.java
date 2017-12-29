package com.lms.app.dao;

import com.lms.app.vo.BillVo;
import java.util.ArrayList;

public abstract interface AdminDAO
{
  public abstract String updateBulkBillDate(ArrayList<BillVo> paramArrayList)
    throws Exception;
}
