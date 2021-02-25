package com.srb.club.pojo.vo.response;

import com.srb.club.utils.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {
    private String message;
    private int code;
    private T data;

    private ResultVo(T data) {
        this.code = 200;
        this.message = "ok";
        this.data = data;
    }

    private ResultVo(CodeMsg cm) {
        if(cm == null){
            return;
        }
        this.code = cm.getCode();
        this.message = cm.getMessage();
    }

    public static <T> ResultVo<T> success(T data){
        return new ResultVo<T>(data);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVo<T> success(){
        return (ResultVo<T>) success("");
    }

    public static <T> ResultVo<T> error(CodeMsg cm){
        return new ResultVo<T>(cm);
    }

    public static <T> ResultVo<T> error(String msg){
        ResultVo<T> result = new ResultVo<>();
        result.setMessage(msg);
        result.setCode(CodeMsg.ERROR_CODE.getCode());
        return result;
    }
}