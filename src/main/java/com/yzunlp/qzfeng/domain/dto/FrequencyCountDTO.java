package com.yzunlp.qzfeng.domain.dto;

import lombok.Data;

/**
 * @author: Huang
 * @description: TODO
 * @date: 2025/7/29 21:13
 */
@Data
public class FrequencyCountDTO {
    private String frequency; // 频率代码 (A, B, C...)
    private Integer count;    // 对应频率的人数
}
