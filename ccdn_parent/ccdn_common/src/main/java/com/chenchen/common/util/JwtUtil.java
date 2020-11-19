package com.chenchen.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * JWT工具类
 * @ahthor chenchen
 */
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtil {

    // 盐值
    private String key;

    // 过期时间
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles) {

        long timeMillis = System.currentTimeMillis();
        Date now = new Date(timeMillis);
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(timeMillis + ttl));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解析JWT
     * @param jwt
     * @return
     */
    public Claims parseJWT(String jwt) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
