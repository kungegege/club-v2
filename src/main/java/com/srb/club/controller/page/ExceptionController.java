package com.srb.club.controller.page;

import com.srb.club.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(TokenException.class)
    public String globalException(HttpServletRequest request, Model model) {
        String url = request.getRequestURI();
        log.warn("token失效，访问路径：",url);
        model.addAttribute("errMsg","登录已过期，请重新登录" );
        model.addAttribute("url",url);
        return "pub/login";
    }


//        @ResponseBody
//        @ExceptionHandler(value = Exception.class)
//        public Map<String, Object> handler(Exception ex) {
//            System.out.print("全局异常捕获："+ex);
//            Map<String, Object> exceptionMap = new HashMap<>();
//            exceptionMap.put("code",0);
//            exceptionMap.put("msg","程序异常被全局异常捕获");
//            return exceptionMap;
//        }

    }
