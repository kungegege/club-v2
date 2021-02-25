package com.srb.club.aop;

import com.srb.club.aop.anno.TimeAno;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeRequestFlag {


    @Before("@annotation(timeAno)")
    public synchronized void beforeRequestFlat(TimeAno timeAno)  {
        System.out.println(timeAno.value());

        boolean flag = true;
        long start = System.currentTimeMillis();
        if (null != System.getProperty("timeFlag")) {//表示这个接口至少被调用一次
            long timeFlag = Long.parseLong(System.getProperty("timeFlag"));
            if (timeFlag + Long.parseLong(timeAno.value()) < start) {//取出来第一次调用的时间戳 加上你规定的时间毫秒数
                //这样如果小于start表示 超过你规定3个小时调用一次的需求 所以需要重新把timeFlag 在刷新一下时间进入下一个3小时算
                System.setProperty("timeFlag", start + "");
//               thorw ....
            }
        } else {//如果else 表示接口第一次调用记录一个时间戳
            System.setProperty("timeFlag", start + "");
        }
    }



//    @After("execution(public * com.srb.club.controller.*.*(..))")
//    public void doAfter(){
//        System.out.println("doAfter");
//    }



}
