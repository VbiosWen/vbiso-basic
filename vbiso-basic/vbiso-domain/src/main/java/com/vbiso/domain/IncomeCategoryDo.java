package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:41 2018/5/7
 * @Modified By:
 */
public class IncomeCategoryDo {

  private long userId;

  private double incomeData;

  private long incomeDate;

  private long categoryId;

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

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }
}
