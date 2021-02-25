package com.srb.club.pojo.mapper;


import com.srb.club.pojo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Insert;

public interface UserInfoMapper {


    @Insert("insert into `user-info`(uid,name) values(#{uid},#{name})")
    void initUserInfo(UserInfoEntity userInfoEntity);

}
