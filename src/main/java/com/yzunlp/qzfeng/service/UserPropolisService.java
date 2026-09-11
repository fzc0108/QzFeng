package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.domain.po.UserPropolis;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;

import java.util.Map;

/**
 * @author 10297
 * @since 2025/7/1 15:53
 */
public interface UserPropolisService {
    /**
     * 添加用户蜂胶信息
     *
     * @param userPropolis 用户蜂胶信息
     */
    void add(UserPropolis userPropolis);

    /**
     * 更新用户蜂胶信息
     *
     * @param userPropolis 用户蜂胶信息
     */
    void update(UserPropolis userPropolis);

    /**
     * 根据用户ID查询蜂胶使用情况（按更新时间降序）
     *
     * @param userId 用户ID
     * @return 用户蜂胶信息
     */
    UserPropolis getByUserId(Long userId);

    YesNoVO propolisYesOrNo();

    YearPeopleVO propolisYear();

    Map<String, Integer> propolisFrequency();
}
