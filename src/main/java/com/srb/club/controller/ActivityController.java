package com.srb.club.controller;

import com.srb.club.pojo.entity.ActivityEntity;
import com.srb.club.pojo.vo.request.ActivityVo;
import com.srb.club.pojo.vo.response.ResultVo;
import com.srb.club.service.ActivityService;
import com.srb.club.service.ClubService;
import com.srb.club.utils.CodeMsg;
import com.srb.club.utils.SnowflakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "活动操作")
@RequestMapping("user/activity")
public class ActivityController  extends BaseController{

    @Autowired
    private  ActivityService activityService;

    @Autowired
    private ClubService clubService;


    @ApiOperation("发布活动")
    @PostMapping("put")
    public Object insertActivity(@RequestBody ActivityVo activityVo, HttpServletRequest request) {

        if (clubService.getClubByCid(activityVo.getCid()) ==null){
            return ResultVo.error(CodeMsg.ILL_PARAM);
        }

        ActivityEntity activityEntity = new ActivityEntity();
        BeanUtils.copyProperties(activityVo, activityEntity);
        activityEntity.setAid(SnowflakeUtil.nextId());

//        activityEntity.setAuthorId(1L);  //测试
        activityEntity.setAuthorId(getUserIdByToken(request));
        activityEntity.setCid(activityVo.getCid());
        activityEntity.setIsActive(1);
        activityEntity.setIsDeleted(0);
        System.err.println(activityEntity);
        Long aid = activityService.insertActivity(activityEntity);
        return ResultVo.success(aid);
    }

    @ApiOperation("查看活动-活动id")
    @GetMapping("getActivity/{aid}")
    public ResultVo selectActivity(@PathVariable  Long aid) {
        if (aid==0){return ResultVo.error("参数错误");}
        ActivityEntity res = activityService.selectActivity(aid);
        return ResultVo.success(res);
    }

    @ApiOperation("查看活动-关键字")
    @GetMapping("getActivityByKeywords")
    public ResultVo selectActivitiesByKeywords( String keywords) {
        if (StringUtils.isEmpty(keywords)){return ResultVo.error("参数错误");}
        List<ActivityEntity> res = activityService.selectActivitiesByKeywords(keywords);
        return ResultVo.success(res);
    }

    @ApiOperation("查看活动-社团id")
    @GetMapping("getActivityByCid")
    public ResultVo selectActivityByCid( Long cid) {
        if (cid==0){return ResultVo.error("参数错误");}
        return ResultVo.success(activityService.selectActivityByCid(cid));
    }

}
