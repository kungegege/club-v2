package com.srb.club.controller.page;

import com.srb.club.pojo.entity.viewEntity.UserViewEntity;
import com.srb.club.pojo.vo.request.LoginVo;
import com.srb.club.utils.CodeMsg;
import com.srb.club.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
public class IndexController {


    @GetMapping("")
    public String index(){
        log.warn("访问了首页...");
        return "index";
    }

    @GetMapping("login")
    public String login(){
        log.warn("访问了登录页面");
        return "pub/login";
    }

    @GetMapping("activity")
    public String activity(){
        log.warn("访问了活动页面");
        return "pub/activity";
    }

    @GetMapping("clubs")
    public String clubs(){
        return "pub/clubs";
    }

    @GetMapping("about")
    public String about(){
        return "pub/about";
    }

    @GetMapping("beside")
    public String beside(){
        return "pub/beside";
    }

    /* 需要进行访问控制 */
    @GetMapping("user/profile")
    public String profile(){
        return "pri/profile";
    }

    @GetMapping("admin")
    public String admin(){
        return "pri/admin";
    }

}
