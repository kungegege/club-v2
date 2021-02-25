package com.srb.club.pojo.mapper;

import com.srb.club.pojo.entity.UserEntity;
import com.srb.club.pojo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

     @Select("select * from user where uid=#{uid}")
     UserEntity getUserByUid(Long uid);

     @Select("select * from user where tel=#{tel}")
     UserEntity getUserByTel(String tel);


     Boolean updateUserInfo(Long uid, UserInfoEntity userInfoEntity);

     @Select("select * from user where tel=#{tel}")
     UserEntity getUser(String tel);

     @Insert("insert into  `user`(uid,tel,`password`,`status`,`salt`) VALUES(#{uid},#{tel},#{password},#{status},#{salt})")
     void resgisterUser(UserEntity userEntity);


}
