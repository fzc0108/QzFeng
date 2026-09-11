package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.domain.dto.LoginDTO;
import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.dto.UserInfoDTO;
import com.yzunlp.qzfeng.domain.vo.LoginVO;
import com.yzunlp.qzfeng.domain.po.*;

public interface UserInfoService {

    LoginVO register(UserHome1stDTO dto);

    void updateUserInfo(UserInfoDTO userInfoDTO);

    LoginVO login(LoginDTO loginDTO);

    UserInfo selectById();

}
