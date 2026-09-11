package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.po.UserHealth;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;

/**
 * @author 10297
 * @since 2025/7/1 15:52
 */
public interface UserHealthService {

    void saveUserHealth(UserHealth userHealth);

    UserHealth getUserHealthById(Long userId);

    Result<YesNoVO> hypertension(String disease);

    Result<YearPeopleVO> hypertensionYear(String disease);

    Result<YesNoVO> hypertensionDrug(String disease);
}
