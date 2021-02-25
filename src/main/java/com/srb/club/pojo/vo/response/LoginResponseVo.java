package com.srb.club.pojo.vo.response;

import lombok.Data;

@Data
public class LoginResponseVo {
    private String token;
    private Long uid;
    private String username;

}
