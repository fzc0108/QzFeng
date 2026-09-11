package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.po.UserEval;

import java.util.Map;

/**
 * @author 10297
 * @since 2025/7/1 15:53
 */
public interface UserEvalService {
    /**
     * 添加用户主观评价
     *
     * @param userEval 用户主观评价
     */
    void save(UserEval userEval);

    /**
     * 更新用户主观评价
     *
     * @param userEval 用户主观评价
     */
    void update(UserEval userEval);

    /**
     * 根据用户ID查询用户主观评价
     *
     * @param userId 用户ID
     * @return 用户主观评价
     */
    UserEval getByUserId(Long userId);

    Map<Integer, Integer> countScoresByEvaluationIndex(Integer eid);
}
