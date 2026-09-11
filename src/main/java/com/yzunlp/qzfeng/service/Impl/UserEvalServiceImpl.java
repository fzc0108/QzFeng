package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.dto.EvaluationCountDTO;
import com.yzunlp.qzfeng.domain.po.UserEval;
import com.yzunlp.qzfeng.mapper.UserEvalMapper;
import com.yzunlp.qzfeng.service.UserEvalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 10297
 * @since 2025/7/1 16:43
 */
@Slf4j
@Service
public class UserEvalServiceImpl implements UserEvalService {

    @Autowired
    private UserEvalMapper userEvalMapper;

    @Override
    public void save(UserEval userEval) {
        userEval.setUserId(BaseContext.getCurrentId());
        if (userEval.getUpdateTime() == null) {
            userEval.setUpdateTime(LocalDateTime.now());
        }
        userEvalMapper.addUserEval(userEval);
    }

    @Override
    public void update(UserEval userEval) {
        userEval.setUpdateTime(LocalDateTime.now());
        userEvalMapper.updateUserEval(userEval);
    }

    @Override
    public UserEval getByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            userId = BaseContext.getCurrentId();
        }
        log.info("service: 查询用户对蜂胶的评价信息(user-id={})", userId);
        return userEvalMapper.selectUserEval(userId);
    }

    @Override
    public Map<Integer, Integer> countScoresByEvaluationIndex(Integer eid) {
        // 1. 参数校验 (非常重要！)
        if (eid == null || eid < 0 || eid > 5) {
            // 或者抛出自定义异常
            log.warn("非法的eid: {}", eid);
            return Map.of(0, 0, 1, 0, 2, 0); // 返回一个空的默认结构
        }

        // 2. 从Mapper获取原始统计数据
        List<EvaluationCountDTO> dbResults = userEvalMapper.countScoresByEvaluationIndex(eid);

        // 3. 将List转换为Map
        Map<Integer, Integer> resultMap = dbResults.stream()
                .collect(Collectors.toMap(
                        EvaluationCountDTO::getOptionValue,
                        EvaluationCountDTO::getCount
                ));

        // 4. (推荐) 确保所有可能的键(0,1,2)都存在，即使它们的计数值是0
        Map<Integer, Integer> finalMap = new HashMap<>();
        finalMap.put(0, resultMap.getOrDefault(0, 0));
        finalMap.put(1, resultMap.getOrDefault(1, 0));
        finalMap.put(2, resultMap.getOrDefault(2, 0));
        // 如果还有选项3，也加上
        if (resultMap.containsKey(3)) {
            finalMap.put(3, resultMap.get(3));
        }

        return finalMap;
    }
}
