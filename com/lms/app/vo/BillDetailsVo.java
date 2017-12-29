package com.lms.app.vo;

public class BillDetailsVo
{
  private int serialno;
  private int clothid;
  private int serviceid;
  private int quanity;
  private float price;
  private float totalprice;
  private String itempaid;
  private String itemPaidDate;
  private String itemdelivered;
  private String itemDeliveredDate;
  private String comments;
  private String clothdesc;
  private String servicedesc;
  private String serviceshortdesc;
  
  public BillDetailsVo()
  {
    this.serialno = 0;
    this.clothid = 0;
    this.serviceid = 0;
    this.quanity = 0;
    this.price = 0.0F;
    this.totalprice = 0.0F;
    this.itempaid = null;
    this.itemPaidDate = null;
    this.itemdelivered = null;
    this.itemDeliveredDate = null;
    this.comments = null;
    this.clothdesc = null;
    this.servicedesc = null;
    this.serviceshortdesc = null;
  }
  
  public String getServiceshortdesc()
  {
    return this.serviceshortdesc;
  }
  
  public void setServiceshortdesc(String serviceshortdesc)
  {
    this.serviceshortdesc = serviceshortdesc;
  }
  
  public int getSerialno()
  {
    return this.serialno;
  }
  
  public void setSerialno(int serialno)
  {
    this.serialno = serialno;
  }
  
  public int getClothid()
  {
    return this.clothid;
  }
  
  public void setClothid(int clothid)
  {
    this.clothid = clothid;
  }
  
  public int getServiceid()
  {
    return this.serviceid;
  }
  
  public void setServiceid(int serviceid)
  {
    this.serviceid = serviceid;
  }
  
  public int getQuanity()
  {
    return this.quanity;
  }
  
  public void setQuanity(int quanity)
  {
    this.quanity = quanity;
  }
  
  public float getPrice()
  {
    return this.price;
  }
  
  public void setPrice(float price)
  {
    this.price = price;
  }
  
  public float getTotalprice()
  {
    return this.totalprice;
  }
  
  public void setTotalprice(float totalprice)
  {
    this.totalprice = totalprice;
  }
  
  public String getItempaid()
  {
    return this.itempaid;
  }
  
  public void setItempaid(String itempaid)
  {
    this.itempaid = itempaid;
  }
  
  public String getItemPaidDate()
  {
    return this.itemPaidDate;
  }
  
  public void setItemPaidDate(String itemPaidDate)
  {
    this.itemPaidDate = itemPaidDate;
  }
  
  public String getItemdelivered()
  {
    return this.itemdelivered;
  }
  
  public void setItemdelivered(String itemdelivered)
  {
    this.itemdelivered = itemdelivered;
  }
  
  public String getItemDeliveredDate()
  {
    return this.itemDeliveredDate;
  }
  
  public void setItemDeliveredDate(String itemDeliveredDate)
  {
    this.itemDeliveredDate = itemDeliveredDate;
  }
  
  public String getComments()
  {
    return this.comments;
  }
  
  public void setComments(String comments)
  {
    this.comments = comments;
  }
  
  public String getClothdesc()
  {
    return this.clothdesc;
  }
  
  public void setClothdesc(String clothdesc)
  {
    this.clothdesc = clothdesc;
  }
  
  public String getServicedesc()
  {
    return this.servicedesc;
  }
  
  public void setServicedesc(String servicedesc)
  {
    this.servicedesc = servicedesc;
  }
}
