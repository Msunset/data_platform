package com.xiangban.data_platform.interceptor;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiangban.data_platform.utils.JWTUtils;
import com.xiangban.data_platform.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     *   进入到controller方法之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accesToken = request.getHeader("token");
            if (accesToken==null){
                accesToken=request.getParameter("token");
            }

            if (StringUtils.isNotBlank(accesToken)){
                Claims claims = JWTUtils.checkJWT(accesToken);
                if (claims==null){
                    //登录过期，重新登陆。
                    sendJsonMessage(response, JsonData.buildError("登录过期，请重新登录！"));

                    return false;
                }

                Integer id =(Integer) claims.get("userid");
                String  name =(String) claims.get("nickname");

                request.setAttribute("userid",id);
                request.setAttribute("nickname",name);

                return true;

            }
        }catch (Exception e){}
        //登录失败
        sendJsonMessage(response, JsonData.buildError("登录过期，请重新登录！"));
        return false;
    }

    /**
     * 响应json数据给前端
     * @param response
     * @param object
     */
    public static void sendJsonMessage(HttpServletResponse response,Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(objectMapper.writeValueAsString(object));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
