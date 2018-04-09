package com.vbiso.webexception;

public class SysException extends BaseException {
    
    public SysException(Integer errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public SysException(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
