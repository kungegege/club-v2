package com.srb.club.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/*
* JWT 生成 token
* */
public class JWTUtil {
    private static  final  String SING = "!#@q41(()(@#@*&^$%@&!@<>>><<?:{";

    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,3);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())  // 过期时间
                .sign(Algorithm.HMAC256(SING));//密钥
        return token;
    }

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    public static DecodedJWT getTokenInfo(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
