package com.yzunlp.qzfeng.domain.dto;

import lombok.Data;

/**
 * @author: Huang
 * @description: TODO
 * @date: 2025/7/29 22:03
 */
@Data
public class EvaluationCountDTO {
    private Integer optionValue; // 选项的值 (0, 1, 2)
    private Integer count;       // 选该选项的人数
}
