package com.shanglei.springCloud.app.common.config;

import com.shanglei.springCloud.core.exception.GlobalException;
import com.shanglei.springCloud.core.exception.GlobalExceptionCode;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
/*@Configuration*/
public class ErrorDecoderConfig implements ErrorDecoder {

    /**
     * 对 feign 异常进行统一处理
     * feign 接口不会抛出自定义异常信息
     * @param s
     * @param response
     * @return
     */
    @Override
    public Exception decode(String s, Response response) {

        log.info(s);

        try {
            log.error("Feign 异常：[{}] 请求失败[{}]", s, Util.toString(response.body().asReader()));
        } catch (IOException e) {   //解析响应结果异常
            e.printStackTrace();
        }

        throw new GlobalException(GlobalExceptionCode.ERROR, s);
    }


}
