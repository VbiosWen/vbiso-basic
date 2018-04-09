package com.vbiso.result;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午3:17 2018/3/13
 * @Modified By:
 */
public class ServiceResult<T> {

  private int code;

  private boolean isSuccess;

  private String msg;

  private T Data;

  private Throwable ex;

  public ServiceResult() {
    this.isSuccess=false;
    this.code=-1;
  }

  public T getData() {
    return Data;
  }

  public void setData(T data) {
    Data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Throwable getEx() {
    return ex;
  }

  public void setEx(Throwable ex) {
    this.ex = ex;
  }
}
