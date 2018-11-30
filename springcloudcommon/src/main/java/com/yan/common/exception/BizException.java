package com.yan.common.exception;

import lombok.Data;

/**
 * <p>类名:BizException </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/30
 * 创建时间: 17:48
 */
@Data
public class BizException extends RuntimeException {
    String code;
    String message;


    public BizException(String code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


}
