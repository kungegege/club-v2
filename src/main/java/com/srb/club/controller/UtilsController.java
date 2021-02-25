package com.srb.club.controller;

import cn.hutool.core.lang.Validator;
import com.srb.club.aop.anno.TimeAno;
import com.srb.club.pojo.vo.response.ResultVo;
import com.srb.club.service.UserService;
import com.srb.club.utils.AliCloudOSSUtil;
import com.srb.club.utils.CodeMsg;
import com.srb.club.utils.TelCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UtilsController extends BaseController{
    @Autowired
    private UserService userService;


    @ApiOperation("手机验证码")
    @GetMapping("pub/phoneCode")
//    @TimeAno("10")
    public ResultVo sendPhoneCode(String tel, HttpServletRequest request) {

        if (!Validator.isMobile(tel)) {
            return ResultVo.error(CodeMsg.TELNUMBER_ERROR);
        }else if(userService.getUserByTel(tel)!=null){
            return ResultVo.error(CodeMsg.USER_TEL_ESIST);
        }

        Map<String, Object> res  = TelCodeUtil.sendMsgCode(tel, request);
        return ResultVo.success(res);
    }

    @ApiOperation("文件上传")
    @GetMapping("user/pushFile")
    public ResultVo pushFile(@RequestParam("file") MultipartFile file,HttpServletRequest request){
//        /*文件存放路径拼接*/
        String filename = "user/"+  getUserIdByToken(request) +"/"+ file.getResource().getFilename();
//
//        /*是否存在判断*/
        if (AliCloudOSSUtil.IsExists(filename)) {
            return ResultVo.error(CodeMsg.FILE_EXIST);
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVo.error("上传失败");
        }
        String url = AliCloudOSSUtil.uploadFile(inputStream, filename);

        HashMap<String, Object> map = new HashMap<>();
        map.put("url",url);
        return ResultVo.success(map);
    }

}
