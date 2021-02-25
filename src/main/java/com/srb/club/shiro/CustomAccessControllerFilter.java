package com.srb.club.shiro;

import com.srb.club.constants.Constant;
import com.srb.club.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomAccessControllerFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("请求方式：", request.getMethod());
        log.info("请求地址：", request.getRequestURI());
        String token = request.getHeader(Constant.SESSION_ID);// 获取用户凭证
        if (StringUtils.isEmpty(token)){
            throw new RRException("用户凭证失效");
        }
        CustomUsernamePasswordToken customUsernamePasswordToken = new CustomUsernamePasswordToken(token);
        getSubject(servletRequest,servletResponse).login(customUsernamePasswordToken);
        return false;
    }
}
