package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:29 2018/5/9
 * @Modified By:
 */
public class ExpensesCountDo {

  private double expensesData;

  private long userId;

  private long expensesDate;

  public double getExpensesData() {
    return expensesData;
  }

  public void setExpensesData(double expensesData) {
    this.expensesData = expensesData;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getExpensesDate() {
    return expensesDate;
  }

  public void setExpensesDate(long expensesDate) {
    this.expensesDate = expensesDate;
  }
}
