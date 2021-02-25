package com.srb.club.service;


import com.srb.club.pojo.entity.UserEntity;
import com.srb.club.pojo.entity.UserInfoEntity;
import com.srb.club.pojo.entity.viewEntity.UserViewEntity;

public interface UserService {

    public UserEntity getUserByUid(Long uid);

    public UserEntity getUserByTel(String tel);

    public  Boolean SimpleResgister(String tel,String pwd);

    public Boolean updateUserInfo(Long uid, UserInfoEntity userInfoEntity);

    public  void resgisterUser(UserEntity userEntity,UserInfoEntity userInfoEntity);

    public UserViewEntity getUserView(String tel);

}
