package com.vbiso.domain;

import java.math.BigDecimal;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午6:01 2018/5/9
 * @Modified By:
 */
public class EveryExpensesDo {

  private long userId;

  private long categoryId;

  private double expensesData;

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
    this.expensesData = new BigDecimal(expensesData).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
  }
}
