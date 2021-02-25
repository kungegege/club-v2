package com.srb.club.aop.anno;


import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeAno {
    String value() default "3600000";  // 1个小时
}
