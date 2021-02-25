package com.srb.club.exception;

import lombok.Getter;

/**
 * @ClassName: BusinessException
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Getter
public class BusinessException extends RuntimeException{

    private final int messageCode;

    private final String messageDefault;

    public BusinessException(int messageCode,String message ) {
        super(message);
        this.messageCode = messageCode;
        this.messageDefault = message;
    }


}
