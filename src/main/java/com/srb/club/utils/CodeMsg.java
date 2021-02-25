package com.srb.club.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
    private int code;
    private String message;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500110, "服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500111, "输入参数为空");
    public static CodeMsg CODE_ERROR = new CodeMsg(500112, "验证码错误");
    public static CodeMsg TELNUMBER_ERROR = new CodeMsg(500113, "非法手机号");
    public static CodeMsg USER_REGIST_ERROR = new CodeMsg(500114, "用户注册，未知错误");
    public static CodeMsg USER_PDW_ERROR = new CodeMsg(500115, "账号或密码错误");
    public static CodeMsg ILL_PARAM = new CodeMsg(500116, "非法参数");
    public static CodeMsg DATA_DIFF = new CodeMsg(500117, "数据不一致");
    public static CodeMsg UN_USEFUL_USER = new CodeMsg(500118, "该账号已被禁用请联系系统管理员");



    // 业务异常
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102, "用户不存在");
    public static CodeMsg ONLINE_USER_OVER = new CodeMsg(500103, "在线用户数超出允许登录的最大用户限制。");
    public static CodeMsg SESSION_NOT_EXSIST = new CodeMsg(500104, "不存在离线session数据");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(500105, "查找不到对应数据");
    public static CodeMsg USER_TEL_ESIST = new CodeMsg(500106, "该手机号已被注册");
    public static CodeMsg Tel_CODE_ERROR = new CodeMsg(500107, "验证码错误");
    public static CodeMsg USER_UNDINDED = new CodeMsg(500108, "请先登录");
    public static CodeMsg ILL_OPERATION = new CodeMsg(500109, "非法操作");

    /*文件*/
    public static CodeMsg FILE_EXIST = new CodeMsg(500200, "已存在同名的文件");

    /*其他*/
    public static CodeMsg ERROR_CODE = new CodeMsg(500000, "错误");

}
