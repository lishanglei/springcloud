package com.shanglei.springCloud.app.common.feign.fallback;

import com.shanglei.springCloud.app.common.feign.UserClient;
import com.shanglei.springCloud.core.exception.GlobalExceptionCode;
import com.shanglei.springCloud.core.wrapper.GlobalResponseWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户
 * 熔断
 * 测试例子,后期删除
 */
@Slf4j
/*@Component*/
public class UserClientFallback implements UserClient {


    /**
     * 若进入熔断则提示资金密码异常
     * @param fundsPassword 要验证的资金密码
     * @return
     */
    @Override
    public GlobalResponseWrapper fundsPasswordCheck(String fundsPassword) {
        log.error("资金密码验证失败，用户服务异常");
        return GlobalResponseWrapper.ok(GlobalExceptionCode.ERROR);
    }


}
