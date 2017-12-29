package com.lms.app.dao;

import com.lms.app.vo.LoginVo;
import java.util.ArrayList;

public abstract interface LoginDAO
{
  public abstract LoginVo login(LoginVo paramLoginVo)
    throws Exception;
  
  public abstract ArrayList getLMSMasterData()
    throws Exception;
}
