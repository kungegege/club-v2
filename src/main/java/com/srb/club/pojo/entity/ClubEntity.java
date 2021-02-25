package com.srb.club.pojo.entity;

import lombok.Data;

@Data
public class ClubEntity {

  private long cid;
  private String clubName;
  private long hostId;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


}
