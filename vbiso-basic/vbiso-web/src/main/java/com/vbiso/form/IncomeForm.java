package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午2:51 2018/4/18
 * @Modified By:
 */
public class IncomeForm {

  private long incomeDate;

  private double incomeData;

  private String file;

  private String desc;

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public long getIncomeDate() {
    return incomeDate;
  }

  public void setIncomeDate(long incomeDate) {
    this.incomeDate = incomeDate;
  }

  public double getIncomeData() {
    return incomeData;
  }

  public void setIncomeData(double incomeData) {
    this.incomeData = incomeData;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }
}
