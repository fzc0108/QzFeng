package com.yzunlp.qzfeng.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author 10297
 * @since 2025/6/25 23:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckupForm {
    private Long id; // 唯一表示号
    private Long userId; // 外键--user_info-id
    private String picUrl; // 体检单(图片)地址
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 上传时间
}
