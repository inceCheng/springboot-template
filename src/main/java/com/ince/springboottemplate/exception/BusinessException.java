package com.ince.springboottemplate.exception;


import com.ince.springboottemplate.enums.ErrorCode;

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(description);  // 使用自定义描述作为异常消息
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
