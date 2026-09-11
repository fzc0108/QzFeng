package com.yzunlp.qzfeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/29 13:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearPeopleVO {
    // 两个list的长度恒等
    private List<Integer> year;
    private List<Integer> people;
}
