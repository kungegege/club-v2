package com.srb.club.pojo.entity;

import lombok.Data;

@Data
public class UserEntity {

  private long uid;
  private String tel;
  private String salt;
  private Integer status=1;
  private String password;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
