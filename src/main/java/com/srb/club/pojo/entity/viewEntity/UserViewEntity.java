package com.srb.club.pojo.entity.viewEntity;

import lombok.Data;


@Data
public class UserViewEntity {
  private long uid;
  private String tel;
  private String name;
  private String portrait;
  private String school;
  private long gender;
  private java.sql.Timestamp updateTime;
  private java.sql.Timestamp createTime;
}
