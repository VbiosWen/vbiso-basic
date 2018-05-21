package com.vbiso.service.result;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:31 2018/5/11
 * @Modified By:
 */
public class InviteCodeResult {

  private int code;

  private String msg;

  private int count;

  private double fee;

  private String unit;

  private String mobile;

  private long sid;


  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public long getSid() {
    return sid;
  }

  public void setSid(long sid) {
    this.sid = sid;
  }
}
