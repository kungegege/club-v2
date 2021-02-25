package com.srb.club.interceptor;

import com.srb.club.exception.TokenException;
import com.srb.club.service.RedisService;
import com.srb.club.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtil.getTokenFromCookie(request);
        System.err.println(token);
        if(StringUtils.isEmpty(token)){
            throw new TokenException(4001002,"用户凭证不能为空，请重新登录");
        }else {
            log.warn("用户凭证异常:",redisService.hasKey(token));
            if(!redisService.hasKey(token)){
                throw new TokenException(4001002,"用户凭证无效，请重新登录");
            }

//            String userId= (String) redisService.get(token);
//            if(redisService.hasKey(userId)&&!token.equals(redisService.get(userId))){
//                throw new TokenException(4001002,"您的账号已经在异地登录，请重新登录");
//            }

        }
        return true;
    }
}
