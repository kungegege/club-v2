package com.srb.club.pojo.entity;

import lombok.Data;

@Data
public class UserInfoEntity {

  private long uid;
  private String name;
  private String portrait;
  private String school;
  private long gender=1;//默认男
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
