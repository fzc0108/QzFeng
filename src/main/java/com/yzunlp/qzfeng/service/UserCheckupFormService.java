package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.domain.po.UserCheckupForm;

/**
 * @author 10297
 * @since 2025/7/1 15:54
 */
public interface UserCheckupFormService {
    /**
     * 添加体检单信息
     *
     * @param checkupForm 体检单信息
     */
    void add(UserCheckupForm checkupForm);

    /**
     * 更新体检单信息
     *
     * @param checkupForm 体检单信息
     */
    void update(UserCheckupForm checkupForm);

    /**
     * 根据用户ID查询体检单信息
     *
     * @param userId 用户ID
     * @return 体检单信息
     */
    UserCheckupForm getByUserId(Long userId);
}
