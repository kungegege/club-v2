//package com.srb.club.interceptor;
//
//import cn.hutool.json.JSONUtil;
//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.srb.club.pojo.vo.response.ResultVo;
//import com.srb.club.utils.JWTUtil;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class JWTInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        ResultVo result;
//
//        /*获取请求头中的token令牌*/
//        String token = request.getHeader("token");
//        System.err.println(token);
//        try {
//            JWTUtil.verify(token);
//            return true; // 放行
//        } catch (TokenExpiredException e) {
//            /*token过期*/
//            result = ResultVo.error("登录已过期，请重新登录");
//        } catch (AlgorithmMismatchException e) {
//            /*token无法解析*/
//            result = ResultVo.error("非法操作");
//        } catch (Exception e) {
//            result = ResultVo.error("操作失败");
//            /*操作失败*/
//        }
//
//        String json = JSONUtil.parseObj(result, false).toStringPretty();
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().println(json);
//        return false;
//    }
//}
