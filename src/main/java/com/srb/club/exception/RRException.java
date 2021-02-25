package com.srb.club.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by Administrator on 2017/8/9.
 * 自定义异常
 */
@Data
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String err;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public RRException(String err) {
        super(err);
        this.err = err;
    }

    public RRException(String err, HttpStatus status) {
        super(err);
        this.err = err;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}