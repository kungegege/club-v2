package com.srb.club.pojo.entity;


import lombok.Data;

@Data
public class ActivityEntity {

  private long aid;
  private long cid;
  private String title;
  private long authorId;
  private String content;
  //审核状态：1为通过，0未为通过，3为待确定
  private long isActive;
  //逻辑删除：1为删除，0为有效-默认
  private long isDeleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
