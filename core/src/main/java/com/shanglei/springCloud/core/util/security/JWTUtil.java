package com.shanglei.springCloud.core.util.security;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 * 单利模式
 * 使用HS512加密算法
 * 单例
 */
public enum JWTUtil {


    INSTANCE;


    /**
     * 生成JWT TOKEN
     *
     * @param map 需要保存到 TOKEN 中的信息
     * @param secret 密钥，加密算法需要，对称加密
     * @return 生成的JWT TOKEN
     */
    public String generate(Map<String,Object> map, String secret) {
        return this.generate(map, secret, null);
    }


    /**
     * 生成JWT TOKEN
     *
     * @param map 需要保存到 TOKEN 中的信息
     * @param sign 签名密钥，加密算法需要，对称加密
     * @param exp 过期时间，若为 null 则代表不设置过期时间
     * @return 生成的JWT TOKEN
     */
    public String generate(Map<String,Object> map, String sign,Date exp) {


        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, sign);

        if (exp != null) {
            jwtBuilder.setExpiration(exp);
        }

        return jwtBuilder.compact();

    }


    /**
     * 验证JWT TOKEN
     * @param token     要验证的TOKEN
     * @param sign    签名密钥，解密需要，对称解密
     * @return
     *      TOKEN验证成功返回JWTResult对象
     * @throws ExpiredJwtException  TOKEN过期抛错
     * @throws SignatureException   签名验证失败抛错
     */
    public JWT check(String token, String sign) throws ExpiredJwtException, SignatureException {

        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(sign)
                    .parseClaimsJws(token)
                    .getBody();

            return new JWT(0, body);
        } catch (ExpiredJwtException e) {       //若TOKEN过期则直接抛错
            return new JWT(1, null);
        } catch (SignatureException e) {        //若签名验证失败则直接抛错
            return new JWT(2, null);
        }catch (Exception e) {
            return new JWT(3, null);
        }

    }

    /**
     * TOKEN 解析结果
     */
    public class JWT {

        /**
         * 结果解析状态
         *         0：正常
         *         1：TOKEN 已过期
         *         2：签名验证失败
         *         3：未知其它异常
         */
        private int status;

        /**
         * 当且仅当 status = 0 时候，该属性有意义
         */
        private Map<String, Object> body;

        public JWT(int status, Map<String, Object> body) {
            this.status = status;
            this.body = body;
        }

        /**
         * 根据 key 获取 body 中的 value
         * @param key
         * @return
         */
        public Object getParam(String key) {
            return this.body.get(key);
        }

        public int getStatus() {
            return status;
        }

        public Map<String, Object> getBody() {
            return body;
        }

        @Override
        public String toString() {
            return "JWT{" +
                    "status=" + status +
                    ", body=" + body +
                    '}';
        }
    }


}
