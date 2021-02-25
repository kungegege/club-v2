package com.srb.club.service;

import com.srb.club.pojo.entity.ActivityEntity;

import java.util.List;

public interface ActivityService {

    Long insertActivity(ActivityEntity activityEntity);

    ActivityEntity selectActivity(Long aid);

    List<ActivityEntity> selectActivitiesByKeywords(String keywords);

    ActivityEntity selectActivityByCid(Long cid);
}
