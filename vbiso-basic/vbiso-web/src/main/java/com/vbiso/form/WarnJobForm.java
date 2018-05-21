package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:13 2018/5/17
 * @Modified By:
 */
public class WarnJobForm {

  private double categoryData;

  private int open;

  private long userId;

  private String startDate;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public double getCategoryData() {
    return categoryData;
  }

  public void setCategoryData(double categoryData) {
    this.categoryData = categoryData;
  }

  public int getOpen() {
    return open;
  }

  public void setOpen(int open) {
    this.open = open;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
