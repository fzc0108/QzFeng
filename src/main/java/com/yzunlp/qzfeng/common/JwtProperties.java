package com.yzunlp.qzfeng.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yzunlp.jwt")
@Data
public class JwtProperties {

    private String userSecretKey;
    private long userTtl; //过期时间
    private String userTokenName;  //前端传递的令牌名称

    public JwtProperties() {
    }

    public JwtProperties(String userSecretKey, long userTtl, String userTokenName) {
        this.userSecretKey = userSecretKey;
        this.userTtl = userTtl;
        this.userTokenName = userTokenName;
    }
}
