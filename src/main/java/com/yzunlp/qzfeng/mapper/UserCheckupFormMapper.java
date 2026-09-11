package com.yzunlp.qzfeng.mapper;

import com.yzunlp.qzfeng.domain.po.UserCheckupForm;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 10297
 * @since 2025/7/1 16:27
 */
@Mapper
public interface UserCheckupFormMapper {
    /**
     * 添加用户体检单信息
     *
     * @param userCheckupForm 用户体检单信息
     */
    void addUserCheckupForm(UserCheckupForm userCheckupForm);

    /**
     * 修改用户体检单信息
     *
     * @param userCheckupForm 用户体检单信息
     */
    void updateUserCheckupForm(UserCheckupForm userCheckupForm);

    /**
     * 查询用户体检单信息
     *
     * @param userId 用户ID
     * @return 用户体检单信息
     */
    UserCheckupForm selectUserCheckupForm(Long userId);
}
