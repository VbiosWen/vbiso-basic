package com.vbiso.webexception.enums;

/**
 * 业务异常基础枚举类
 */
public enum BaseBusinessExceptionEnum {
    INVALID_PRARM(8003,"表单错误");

    private Integer errorCode;

    private String errorMsg;

    BaseBusinessExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
