package com.vbiso.exception;


/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午8:19 2018/4/17
 * @Modified By:
 */
public class BaseException extends RuntimeException {


  public BaseException() {
    super();
  }

  public BaseException(String msg){
    super(msg);
  }

  public BaseException(String msg,Throwable cause){
    super(msg,cause);
  }
  public BaseException(Throwable cause){
    super(cause);
  }
}
