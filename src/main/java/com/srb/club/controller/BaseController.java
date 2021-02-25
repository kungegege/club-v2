package com.srb.club.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.srb.club.constants.Constant;
import com.srb.club.utils.IpUtil;
import com.srb.club.utils.JWTUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    /*
     *  获取请求的ip地址
    * */
    protected final String getIpAddr(HttpServletRequest request) {
        return  IpUtil.getRealIp(request);
    }

    /*
     * 获取userId
     */
    @Deprecated
    public long getUserIdByToken(HttpServletRequest request) {
        DecodedJWT token = JWTUtil.getTokenInfo(request.getHeader("token"));
        return Long.parseLong(token.getClaim("uid").asString());
    }


}
