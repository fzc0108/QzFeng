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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPropolis {
    private Long id; // 蜂胶使用情况 唯一标识号
    private Boolean propolis; // 是否服用过蜂胶
    private Short propolisYear; // 从何时开始使用蜂胶
    private String propolisFrequency; // 蜂胶使用频率(单选: ABCD)
    private Long userId; // 外键--user_info--id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 上传时间
}
