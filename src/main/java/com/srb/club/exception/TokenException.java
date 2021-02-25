package com.srb.club.exception;

public class TokenException extends BusinessException{
    public TokenException() {
        super(4001004, "token异常");
    }

    public TokenException(int messageCode, String message) {
        super(messageCode, message);
    }
}
