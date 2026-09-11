package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.common.PageResult;
import com.yzunlp.qzfeng.domain.vo.UserInfoHealthVO;
import com.yzunlp.qzfeng.domain.vo.UserQuestionnaireVO;

public interface AdminService {

    UserInfoHealthVO selectUserInfoHealthById(Long id);

    UserQuestionnaireVO selectUserQuestionnaireById(Long id);

    PageResult getAllUsers(int pageNum, int pageSize);
}
