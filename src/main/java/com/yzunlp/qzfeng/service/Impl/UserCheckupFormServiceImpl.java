package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.domain.po.UserCheckupForm;
import com.yzunlp.qzfeng.mapper.UserCheckupFormMapper;
import com.yzunlp.qzfeng.service.UserCheckupFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author 10297
 * @since 2025/7/1 16:44
 */
@Slf4j
@Service
public class UserCheckupFormServiceImpl implements UserCheckupFormService {

    @Autowired
    private UserCheckupFormMapper checkupFormMapper;

    @Override
    public void add(UserCheckupForm checkupForm) {
        if (checkupForm.getUpdateTime() == null) {
            checkupForm.setUpdateTime(LocalDateTime.now());
        }
        checkupFormMapper.addUserCheckupForm(checkupForm);
    }

    @Override
    public void update(UserCheckupForm checkupForm) {
        checkupForm.setUpdateTime(LocalDateTime.now());
        checkupFormMapper.updateUserCheckupForm(checkupForm);
    }

    @Override
    public UserCheckupForm getByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            userId = BaseContext.getCurrentId();
        }
        log.info("service: 查询用户的体检报告(user-id={})", userId);
        return checkupFormMapper.selectUserCheckupForm(userId);
    }
}
