package com.yzunlp.qzfeng.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 10297
 * @since 2025/7/14 21:07
 */
@Component
@ConfigurationProperties(prefix = "yzunlp.upload")
@Data
public class UploadProperties {
    private String user_upload_path;

    public UploadProperties() {
    }

    public UploadProperties(String user_upload_path) {
        this.user_upload_path = user_upload_path;
    }
}
