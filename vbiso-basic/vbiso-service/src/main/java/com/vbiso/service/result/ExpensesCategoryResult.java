package com.vbiso.service.result;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午8:32 2018/5/9
 * @Modified By:
 */
public class ExpensesCategoryResult implements Comparable<ExpensesCategoryResult> {

  private long userId;

  private long expensesDate;

  private String categoryDesc;

  private double expensesData;

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

  public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }

  public double getExpensesData() {
    return expensesData;
  }

  public void setExpensesData(double expensesData) {
    this.expensesData = expensesData;
  }

  @Override
  public int compareTo(ExpensesCategoryResult o) {
    return (int) (this.expensesDate-o.expensesDate);
  }
}
