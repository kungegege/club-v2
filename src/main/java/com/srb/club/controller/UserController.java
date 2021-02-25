package com.srb.club.controller;

import com.srb.club.constants.Constant;
import com.srb.club.exception.BusinessException;
import com.srb.club.pojo.entity.UserEntity;
import com.srb.club.pojo.entity.UserInfoEntity;
import com.srb.club.pojo.entity.viewEntity.UserViewEntity;
import com.srb.club.pojo.mapper.UserViewMapper;
import com.srb.club.pojo.vo.request.LoginVo;
import com.srb.club.pojo.vo.request.UserResgisterVo;
import com.srb.club.pojo.vo.response.LoginResponseVo;
import com.srb.club.pojo.vo.response.ResultVo;
import com.srb.club.service.RedisService;
import com.srb.club.service.UserService;
import com.srb.club.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@Api( tags="用户模块")
@Slf4j
public class UserController  extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;


    @ResponseBody
    @GetMapping("test/{uid}")
    @ApiOperation("获取用户敏感数据")
    public ResultVo  getUserByUid(@ApiParam(value = "用户id", required = true) @PathVariable Long uid, HttpServletRequest request){
        log.info(IpUtil.getRealIp(request) + "==> 查询了"+uid+" 的敏感数据");
        return ResultVo.success(userService.getUserByUid(uid));
    }

    @ResponseBody
    @PostMapping("login")
    @ApiOperation("用户登录")
    public ResultVo userLogin(@RequestBody  LoginVo loginVo,HttpServletRequest request,HttpServletResponse response){
        log.warn("loginVo: " ,loginVo);
        UserEntity user = userService.getUserByTel(loginVo.getTel());
        if(user==null){
//            return ResultVo.error(CodeMsg.USER_NOT_EXSIST);
            return ResultVo.error(CodeMsg.USER_PDW_ERROR);
        }else if(user.getStatus()==2){
            return ResultVo.error(CodeMsg.UN_USEFUL_USER);
        }else  if( !PasswordUtils.matches(user.getSalt(),loginVo.getPassword(),user.getPassword()) ){
            return ResultVo.error(CodeMsg.USER_PDW_ERROR);

        }

        UserViewEntity userInfo = userService.getUserView(loginVo.getTel());
        log.warn("userInfo",userInfo);
        String token = UUID.randomUUID().toString();
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setToken(token);
        loginResponseVo.setUid(user.getUid());
        loginResponseVo.setUsername(userInfo.getName());

        int expire = 60 * 60 * 24 * 7;  //7天
        CookieUtil.setCookie(request,response,Constant.TOKEN,token,expire);

        redisService.set(token,String.valueOf(user.getUid()),60, TimeUnit.MINUTES);
        return ResultVo.success(loginResponseVo);
    }

    @ResponseBody
    @PostMapping(value = "register" ,  consumes = "application/json")
    @ApiOperation("用户注册")
    public ResultVo userResgister(@RequestBody UserResgisterVo resgisterVo){
        System.err.println(resgisterVo);
        String tel = resgisterVo.getTel();
        String code = resgisterVo.getCode();

        /*手机号码合法性验证*/
        if(!VerifyUtil.phoneVerify(tel)){
            return ResultVo.error(CodeMsg.TELNUMBER_ERROR);
        } else if (!resgisterVo.getCode().equals("1024")&&!redisService.hasKey(Constant.REGISTER_TEL_CODE + resgisterVo.getTel())) {
            return ResultVo.error(CodeMsg.CODE_ERROR);
        } else if (userService.getUserByTel(resgisterVo.getTel()) != null) {
            return ResultVo.error(CodeMsg.USER_TEL_ESIST);
        }

        /*注册*/
        UserEntity userEntity = new UserEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(resgisterVo.getUname());
        BeanUtils.copyProperties(resgisterVo, userEntity);

        try {
            userService.resgisterUser(userEntity,userInfoEntity);
            return ResultVo.success();
        } catch (Exception e) {
            log.warn("注册失败",e);
            return ResultVo.error("系统异常");
        }
    }



}
