package com.srb.club.service.impl;

import com.srb.club.pojo.entity.UserEntity;
import com.srb.club.pojo.entity.UserInfoEntity;
import com.srb.club.pojo.entity.viewEntity.UserViewEntity;
import com.srb.club.pojo.mapper.UserInfoMapper;
import com.srb.club.pojo.mapper.UserMapper;
import com.srb.club.pojo.mapper.UserViewMapper;
import com.srb.club.service.UserService;
import com.srb.club.utils.PasswordUtils;
import com.srb.club.utils.SnowflakeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserViewMapper userViewMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserEntity getUserByUid(Long uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public UserEntity getUserByTel(String tel) {
        return userMapper.getUserByTel(tel);
    }


    @Override
    public Boolean SimpleResgister(String tel, String pwd) {
        return null;
    }

    @Override
    public Boolean updateUserInfo(Long uid, UserInfoEntity userInfoEntity) {
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resgisterUser(UserEntity userEntity,UserInfoEntity userInfoEntity) {
        String salt = PasswordUtils.getSalt();
        if (userEntity.getUid()==0||userInfoEntity.getUid()==0){
            long uid = SnowflakeUtil.nextId();
            userEntity.setUid(uid);
            userInfoEntity.setUid(uid);
        }
        userEntity.setSalt(salt);
        userEntity.setPassword(PasswordUtils.encode(userEntity.getPassword(), salt)); //加密
        userEntity.setStatus(1);
        userMapper.resgisterUser(userEntity);
        userInfoMapper.initUserInfo(userInfoEntity);
        return;
    }

    @Override
    public UserViewEntity getUserView(String tel) {
        return userViewMapper.getUser(tel);
    }

}
