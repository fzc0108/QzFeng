package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.JwtProperties;
import com.yzunlp.qzfeng.common.JwtUtil;
import com.yzunlp.qzfeng.domain.dto.LoginDTO;
import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.dto.UserInfoDTO;
import com.yzunlp.qzfeng.domain.po.UserInfo;
import com.yzunlp.qzfeng.domain.po.UserHealth;
import com.yzunlp.qzfeng.domain.po.UserEval;
import com.yzunlp.qzfeng.domain.po.UserPropolis;
import com.yzunlp.qzfeng.domain.vo.LoginVO;
import com.yzunlp.qzfeng.mapper.UserInfoMapper;
import com.yzunlp.qzfeng.mapper.UserHealthMapper;
import com.yzunlp.qzfeng.mapper.UserEvalMapper;
import com.yzunlp.qzfeng.mapper.UserPropolisMapper;
import com.yzunlp.qzfeng.service.UserInfoService;
import com.yzunlp.qzfeng.mapper.UserCheckupFormMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserHealthMapper userHealthMapper;
    @Autowired
    private UserEvalMapper userEvalMapper;
    @Autowired
    private UserPropolisMapper userPropolisMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserCheckupFormMapper userCheckupFormMapper;

    /**
     * 注册功能
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginVO register(UserHome1stDTO dto) {

        // 1. 查重
        if (userInfoMapper.selectByPhone(dto.getPhone()) != null) {
            throw new RuntimeException("该手机号已注册，请直接登录");
        }

        // 2. 保存【用户基本信息】
        UserInfo user = UserInfo.builder()
                .phone(dto.getPhone())
                .name(dto.getName())
                .userCode(dto.getUserCode())
                .password("yzunlp")
                .sex(dto.getSex())
                .birthday(dto.getBirthday())
                .areaCode(dto.getAreaCode())
                .build();

        userInfoMapper.insert(user);
        Long userId = user.getId();

        // 3. 保存【健康状况】
        UserHealth health = UserHealth.builder()
                .hypertension(dto.getHypertension())
                .hypertensionYear(dto.getHypertensionYear())
                .hypertensionDrug(dto.getHypertensionDrug())
                .diabetes(dto.getDiabetes())
                .diabetesYear(dto.getDiabetesYear())
                .diabetesDrug(dto.getDiabetesDrug())
                .hyperlipidemia(dto.getHyperlipidemia())
                .hyperlipidemiaYear(dto.getHyperlipidemiaYear())
                .hyperlipidemiaDrug(dto.getHyperlipidemiaDrug())
                .tumor(dto.getTumor())
                .build();
        userHealthMapper.addUserHealth(health);

        // 4. 保存【蜂胶使用情况】
        UserPropolis propolis = UserPropolis.builder()
                .userId(userId)
                .propolis(dto.getPropolis())
                .propolisYear(dto.getPropolisYear())
                .propolisFrequency(dto.getPropolisFrequency())
                .updateTime(LocalDateTime.now())
                .build();
        userPropolisMapper.addUserPropolis(propolis);

        // 5. 保存【主观评估】
        if (StringUtils.hasText(dto.getEvaluation())) {
            UserEval eval = UserEval.builder()
                    .userId(userId)
                    .evaluation(dto.getEvaluation())
                    .updateTime(LocalDateTime.now())
                    .build();

            userEvalMapper.addUserEval(eval);
        }

        // 6. 返回 Token
        return generateTokenAndBuildVO(user);
    }

    /**
     * 登录功能
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String phone = loginDTO.getPhone();
        String inputCode = loginDTO.getUserCode();
        UserInfo user = userInfoMapper.selectByPhone(phone);

        // 1. 如果查不到人 -> 返回null -> 前端跳去注册
        if (user == null) {
            return null;
        }

        // 2. 查到了人，但是没填编号 -> 提示他去填
        if (!StringUtils.hasText(inputCode)) {
            throw new RuntimeException("您已注册，请输入您的【登录编号】");
        }

        // 3. 填了编号，但是不对 -> 提示错误
        if (!user.getUserCode().equals(inputCode)) {
            throw new RuntimeException("登录编号错误，请核对！");
        }

        // 4. 全都对 -> 发通行证
        return generateTokenAndBuildVO(user);
    }

    /**
     * 辅助方法：生成 Token
     */
    private LoginVO generateTokenAndBuildVO(UserInfo user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userID", user.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims
        );

        return LoginVO.builder()
                .phone(user.getPhone())
                .token(token)
                .userCode(user.getUserCode())
                .isNewUser(false)
                .build();
    }

    @Override
    public void updateUserInfo(UserInfoDTO userInfoDTO) {
        // 1. 从 Token 获取当前用户 ID
        Long currentUserId = BaseContext.getCurrentId();
        userInfoDTO.setId(currentUserId);
        boolean isAllNull = userInfoDTO.getName() == null
                && userInfoDTO.getPassword() == null
                && userInfoDTO.getChinaId() == null
                && userInfoDTO.getBirthday() == null
                && userInfoDTO.getSex() == null
                && userInfoDTO.getAreaCode() == null;

        if (isAllNull) {
            log.warn("检测到空的更新请求，已拦截。UserID: {}", currentUserId);
            return; // 直接返回
        }

        // 有数据才执行更新
        userInfoMapper.updateUserInfo(userInfoDTO);

    }

    @Override
    public UserInfo selectById() {
        return userInfoMapper.selectById(BaseContext.getCurrentId());
    }


}