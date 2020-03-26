package com.shanglei.springCloud.app.common.handler;

import cn.hutool.core.util.StrUtil;
import com.shanglei.springCloud.core.thread.CurrentUser;
import com.shanglei.springCloud.core.util.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class GlobalRequestHandler implements HandlerInterceptor {


    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.sign}")
    private String jwtSign;

    /**
     * 请求前置拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        /*
        此处进入到此处则认定网关已经进行过认证
        此处允许 token 为空，若为空则代表其要访问的资源不需要授权
         */
        String token = request.getHeader(this.jwtKey);

        if (!StrUtil.isBlank(token)) {
            JWTUtil.JWT jwt = JWTUtil.INSTANCE.check(token, this.jwtSign);
            Map<String,Object> body = jwt.getBody();

            //初始化线程数据
            Long id = Long.parseLong(body.get("id").toString());
            CurrentUser.init(id);
        }

        return Boolean.TRUE;
    }


    /**
     * 请求后置拦截
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //当前请求结束需要销毁线程中存储的内容，否则线程池的作用会导致这些缓存的数据无法被虚拟机销毁
        CurrentUser.destroy();
    }

}
