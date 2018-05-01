package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午2:29 2018/4/24
 * @Modified By:
 */
public class ExpenseForm {

  private long userId;

  private String desc;

  private double expensesData;

  private long expensesDate;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public double getExpensesData() {
    return expensesData;
  }

  public void setExpensesData(double expensesData) {
    this.expensesData = expensesData;
  }

  public long getExpensesDate() {
    return expensesDate;
  }

  public void setExpensesDate(long expensesDate) {
    this.expensesDate = expensesDate;
  }
}
