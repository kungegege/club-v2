package com.srb.club.utils;

import com.aliyun.oss.model.OSSObjectSummary;
import com.srb.club.utils.rely.AliOssCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class AliCloudOSSUtil {
    /*文件上传*/
    public static String uploadFile(InputStream inputStream, String filename) {

        //文件名用了uuid 防止重复
//        String name = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."), filename.length());
//        name = name.replace("-", "");
//        System.out.println("fileName:" + name);
//        System.out.println("开始上传...");
        log.info("开始上传:" + filename);

        AliOssCloudUtil util = new AliOssCloudUtil();
        //上传成功返回完整路径的url
        String url = util.uploadFile2OSS(inputStream, filename);
        return url;
    }

    public static Boolean IsExists(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        AliOssCloudUtil util = new AliOssCloudUtil();
        Boolean bool = util.doesObjectExist(fileName);
        return bool;
    }

    @Deprecated
    public void downloadFile(String fileName, HttpServletResponse response) {
        try {
            AliOssCloudUtil ossClientUtil = new AliOssCloudUtil();
            InputStream is = ossClientUtil.getInputStreamByFileUrl(ossClientUtil.getFiledir() + fileName);
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    用户目录文件信息获取
    * */
    public static List<Map<String, Object>> ListUserFiles(Long uid) {
        AliOssCloudUtil aliOssCloudUtil = new AliOssCloudUtil();
        List<OSSObjectSummary> sums = aliOssCloudUtil.showFiles("public/user/" + uid);
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        for (OSSObjectSummary s : sums) {
            Map<String, Object> map = new HashMap<>();
            map.put("file", s.getKey());
            map.put("size", s.getSize());
            map.put("time", s.getLastModified());
            map.put("user", s.getOwner());
            list.add(map);
        }
        return list;
    }



}
