package com.shanglei.springCloud.app.common.config;


import com.shanglei.springCloud.core.exception.GlobalException;
import com.shanglei.springCloud.core.exception.GlobalExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 配置
 * 用户得到 JedisPool 对象
 */
@Slf4j
@Configuration
public class JedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.timeout}")
    private int redisTimeout;


    /**
     * 初始化 JedisPool
     * 使用默认配置
     * @return
     */
    @Bean
    public JedisPool getJedisPool() {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

            if (StringUtils.isEmpty(this.redisPassword)) {
                return new JedisPool(jedisPoolConfig, this.redisHost, this.redisPort, this.redisTimeout);
            } else {
                return new JedisPool(jedisPoolConfig, this.redisHost, this.redisPort, this.redisTimeout, this.redisPassword);
            }
        } catch (Throwable cause) {
            log.error("Jedis Pool 初始化失败");
            cause.printStackTrace();
            System.exit(0);
            throw new GlobalException(GlobalExceptionCode.JEDIS_POOL_INIT_FAIL);
        }



    }


}
