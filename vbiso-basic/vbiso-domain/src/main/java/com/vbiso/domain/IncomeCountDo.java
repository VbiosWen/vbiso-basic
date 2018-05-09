package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:01 2018/5/3
 * @Modified By:
 */
public class IncomeCountDo {

  private double incomeData;

  private long incomeDate;

  private long userId;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public double getIncomeData() {
    return incomeData;
  }

  public void setIncomeData(double incomeData) {
    this.incomeData = incomeData;
  }

  public long getIncomeDate() {
    return incomeDate;
  }

  public void setIncomeDate(long incomeDate) {
    this.incomeDate = incomeDate;
  }
}
