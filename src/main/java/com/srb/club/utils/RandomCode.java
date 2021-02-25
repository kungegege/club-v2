package com.srb.club.utils;

import cn.hutool.core.util.RandomUtil;

public class RandomCode {

    public static String getRandomCode(){
       return getRandomCode(6);
    }

    public static String getRandomCode(int num){
        if (num<=0){ return "";}
        String code="";
        for (int i=0;i<num;i++){
            code+=RandomUtil.randomInt(1, 10);
        }
        return code;
    }

}
