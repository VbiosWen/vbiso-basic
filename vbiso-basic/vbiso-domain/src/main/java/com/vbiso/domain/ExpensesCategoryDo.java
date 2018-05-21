package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:57 2018/5/9
 * @Modified By:
 */
public class ExpensesCategoryDo {

  private long userId;

  private long categoryId;

  private double expensesData;

  private long expensesDate;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
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
