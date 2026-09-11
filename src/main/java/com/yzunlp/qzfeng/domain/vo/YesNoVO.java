package com.yzunlp.qzfeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 10297
 * @since 2025/7/29 13:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YesNoVO {
    private int yes;  // 是 人数
    private int no;  // 否 人数
}
