package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:13 2018/5/16
 * @Modified By:
 */
public class TimeJobIncomeDo {

  private long userId;

  private long incomeId;

  private long incomeDate;

  private double incomeData;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(long incomeId) {
    this.incomeId = incomeId;
  }

  public long getIncomeDate() {
    return incomeDate;
  }

  public void setIncomeDate(long incomeDate) {
    this.incomeDate = incomeDate;
  }

  public double getIncomeData() {
    return incomeData;
  }

  public void setIncomeData(double incomeData) {
    this.incomeData = incomeData;
  }
}
