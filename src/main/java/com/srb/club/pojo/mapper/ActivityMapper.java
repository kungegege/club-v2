package com.srb.club.pojo.mapper;

import com.srb.club.pojo.entity.ActivityEntity;

import java.util.List;

public interface ActivityMapper {

    void insertActivity(ActivityEntity activityEntity);

    ActivityEntity selectActivityByAid(Long aid);

    List<ActivityEntity> selectActivitiesByKeywords(String keywords);

    List<ActivityEntity> selectActivitiesByClubId(Long cid);


    ActivityEntity selectActivityByCid(Long aid);

}
