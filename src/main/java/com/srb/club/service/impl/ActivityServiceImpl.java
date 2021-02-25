package com.srb.club.service.impl;

import com.srb.club.pojo.entity.ActivityEntity;
import com.srb.club.pojo.mapper.ActivityMapper;
import com.srb.club.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityMapper activityMapper;

    @Override
    public Long insertActivity(ActivityEntity activityEntity) {
        try {
            activityMapper.insertActivity(activityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return activityEntity.getAid();
    }

    @Override
    public ActivityEntity selectActivity(Long aid) {
        return  activityMapper.selectActivityByAid(aid);
    }

    @Override
    public List<ActivityEntity> selectActivitiesByKeywords(String keywords) {
        return activityMapper.selectActivitiesByKeywords(keywords);
    }

    @Override
    public ActivityEntity selectActivityByCid(Long cid) {
        return activityMapper.selectActivityByCid(cid);
    }
}
