package com.yzunlp.qzfeng.mapper;

import com.yzunlp.qzfeng.domain.dto.UserInfoDTO;
import com.yzunlp.qzfeng.domain.dto.LoginDTO;
import com.yzunlp.qzfeng.domain.dto.RegisterDTO;
import com.yzunlp.qzfeng.domain.po.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    int addUser(RegisterDTO registerDTO);

    // 修改用户信息
    void updateUserInfo(UserInfoDTO userInfoDTO);

    // 根据 id 查询用户信息
    UserInfo selectById(Long id);

    // 根据 手机号 查询用户信息
    UserInfo selectByPhone(String phone);

    // 根据 手机号 和 密码 查询用户信息
    UserInfo selectByPhoneAndPassword(LoginDTO loginDTO);

    int insert(UserInfo userInfo);

}