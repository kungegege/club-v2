package com.srb.club.pojo.entity;


import lombok.Data;

@Data
public class ClubInfoEntity {

  private long cid;
  private String des;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
