package com.yzunlp.qzfeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 10297
 * @since 2025/7/10 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHomeDTO {
    private Boolean propolis; // 是否服用过蜂胶
    private Short propolisYear; // 从何时开始使用蜂胶
    private String propolisFrequency; // 蜂胶使用频率(单选: ABCD)
    private String evaluation; // 使用逗号分割，共六个评分(评分范围[0, 3])
    private String picUrl; // 体检单(图片)地址
}
