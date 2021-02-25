package com.srb.club.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.AccessLevel;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class TelCodeUtil {
    private  static String accessKeyId = "LTAI4GJ1mXzosfKkY6ttWiQD";//accessKeyId
    private  static String accessKeySecret = "mFVBAsTN35u5i6rDRefDAj6yY0o4No";//accessKeySecret
    private  static DefaultProfile profile;
    private  static CommonRequest request = new CommonRequest();
    private  static IAcsClient client;
    @Setter( AccessLevel.NONE)
    public  final  static String TEL_CODE = "tel_code";
    public  final  static String IMG_CODE = "img_code";
    public  final  static String TARGET_NUM = "tel_num";


    static {
        /*accessKeyId + accessKeySecret  配置*/
        profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);

        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        /*签名 + 模板配置*/
        request.putQueryParameter("SignName", "toAnswer");
        request.putQueryParameter("TemplateCode", "SMS_204116066");
    }

    public static Map<String,Object> sendMsgCode(String telNumber) {
        String code = RandomCode.getRandomCode();
        return sendMsgCode(telNumber,code);
    }

    @Deprecated
    public static Map<String,Object>  sendMsgCode(String telNumber,String code) {
        HashMap<String, Object> res = new HashMap<>();

        request.putQueryParameter("PhoneNumbers", telNumber);
        request.putQueryParameter("TemplateParam", "{\"code\":" + "\""+ code +"\"" + "}");
//        System.err.println("{\"code\":" + "\""+ code +"\"" + "}");
        CommonResponse commonResponse = null;
        try {
            commonResponse = client.getCommonResponse(request);
            res.put("code",code);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (commonResponse != null) {
            res.put("msg", commonResponse.getData());
        }
        return res;
    }

    public static Map<String,Object>  sendMsgCode(String telNumber, HttpServletRequest request) {
        String code = RandomCode.getRandomCode();
        Map<String, Object> res = sendMsgCode(telNumber, code);
        HttpSession session = request.getSession();
        session.setAttribute(TEL_CODE,code);
        session.setAttribute(TARGET_NUM,telNumber);
        return  res;
    }

}
