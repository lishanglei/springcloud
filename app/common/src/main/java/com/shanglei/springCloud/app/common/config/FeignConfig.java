package com.shanglei.springCloud.app.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign 配置
 */
@Slf4j
@Configuration
public class FeignConfig implements RequestInterceptor {


    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * 解决内部feign调用无token问题
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String token = request.getHeader(this.jwtKey);
            requestTemplate.header(this.jwtKey, token);
        }


    }






}
