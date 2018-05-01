package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午6:46 2018/5/1
 * @Modified By:
 */
public class NetIncomeDo {

  private long netincomeId;

  private long userId;

  private double netincomeData;

  private long netincomeDate;

  public long getNetincomeId() {
    return netincomeId;
  }

  public void setNetincomeId(long netincomeId) {
    this.netincomeId = netincomeId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public double getNetincomeData() {
    return netincomeData;
  }

  public void setNetincomeData(double netincomeData) {
    this.netincomeData = netincomeData;
  }

  public long getNetincomeDate() {
    return netincomeDate;
  }

  public void setNetincomeDate(long netincomeDate) {
    this.netincomeDate = netincomeDate;
  }
}
