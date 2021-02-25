package com.srb.club.pojo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "com.srb.club.pojo.vo.request.UserResgisterVo", description = "用户注册vo")
public class UserResgisterVo  {
    @ApiModelProperty("用户名")
    private String uname;
    @ApiModelProperty("电话号码")
    private String tel;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机验证码")
    private String code;

}
