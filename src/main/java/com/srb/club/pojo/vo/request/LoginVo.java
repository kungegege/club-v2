package com.srb.club.pojo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "com.srb.club.pojo.vo.request.LoginVo",description = "用户登录vo")
public class LoginVo {
    @ApiModelProperty("电话号码")
    private String tel;
    @ApiModelProperty("密码")
    private String password;
}
