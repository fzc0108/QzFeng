package com.yzunlp.qzfeng.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author 10297
 * @since 2025/6/25 23:19
 */
@Data@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEval {
    private Long id; // 主观评价唯一标识号
    private String evaluation; // 使用逗号分割，共六个评分(评分范围[0, 3])
    private Long userId; // 外键--user_info--id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 上传时间
}
