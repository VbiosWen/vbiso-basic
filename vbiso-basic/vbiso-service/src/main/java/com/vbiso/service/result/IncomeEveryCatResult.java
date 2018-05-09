package com.vbiso.service.result;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:08 2018/5/9
 * @Modified By:
 */
public class IncomeEveryCatResult {

  private double sum;

  private long userId;

  private String category;

  public double getSum() {
    return sum;
  }

  public void setSum(double sum) {
    this.sum = sum;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
