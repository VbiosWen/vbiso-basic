package com.vbiso.service.result;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午6:02 2018/5/7
 * @Modified By:
 */
public class IncomeCategoryResult implements Comparable<IncomeCategoryResult> {

  private long userId;

  private String categoryDesc;

  private double incomeData;

  private long incomeDate;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
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

  @Override
  public int compareTo(IncomeCategoryResult o) {
    return (int) (this.incomeDate-o.incomeDate);
  }
}
