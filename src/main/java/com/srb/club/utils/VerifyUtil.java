package com.srb.club.utils;

public class VerifyUtil {

    public static Boolean phoneVerify(String tel){
        String telRegex = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        return tel.matches(telRegex);
    }



}
