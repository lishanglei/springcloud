package com.shanglei.springCloud.app.common.feign;

import com.shanglei.springCloud.core.wrapper.GlobalResponseWrapper;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户
 * 测试例子,后期删除
 */
/*@Primary
@FeignClient(value = "user", configuration = {FeignConfig.class}, fallback = UserClientFallback.class)*/
public interface UserClient {


    /**
     * 资金密码验证
     * @param fundsPassword 要验证的资金密码
     * @return
     */
    /*@GetMapping("security/funds-password/check")*/
    GlobalResponseWrapper fundsPasswordCheck(@RequestParam String fundsPassword);

}
