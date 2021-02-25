package com.srb.club.service.impl;

import com.srb.club.pojo.entity.ClubEntity;
import com.srb.club.pojo.mapper.ClubMapper;
import com.srb.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClubServiceImpl implements ClubService {
    @Resource
    private  ClubMapper clubMapper;


    @Override
    public ClubEntity getClubByCid(Long cid) {
        return  clubMapper.selectClubByCid(cid);
    }
}
