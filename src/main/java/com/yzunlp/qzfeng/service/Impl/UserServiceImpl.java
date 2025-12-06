package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.dto.UserHomeDTO;
import com.yzunlp.qzfeng.domain.dto.UserInfoDTO;
import com.yzunlp.qzfeng.domain.po.*;
import com.yzunlp.qzfeng.mapper.UserInfoMapper;
import com.yzunlp.qzfeng.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.time.LocalDateTime;

/**
 * @author: Huang
 * @description: TODO
 * @date: 2025/7/11 14:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserHealthService userHealthService;
    @Autowired
    private UserEvalService userEvalService;
    @Autowired
    private UserPropolisService userPropolisService;
    @Autowired
    private UserCheckupFormService userCheckupFormService;

    @Override
    @Transactional
    public void saveAll(UserHome1stDTO userHome1stDTO) {
        if (userHome1stDTO.getDiabetesYear() == null) {
            userHome1stDTO.setDiabetesYear(0);
        }
        if (userHome1stDTO.getHypertensionYear() == null) {
            userHome1stDTO.setHypertensionYear(0);
        }
        if (userHome1stDTO.getPropolisYear() == null) {
            userHome1stDTO.setPropolisYear((short) 0);
        }
        if (userHome1stDTO.getHyperlipidemiaYear() == null) {
            userHome1stDTO.setHyperlipidemiaYear(0);
        }
        if (userHome1stDTO.getHypertensionDrug() == null) {
            userHome1stDTO.setHypertensionDrug(false);
        }
        if (userHome1stDTO.getHyperlipidemiaDrug() == null) {
            userHome1stDTO.setHyperlipidemiaDrug(false);
        }
        if (userHome1stDTO.getDiabetesDrug() == null) {
            userHome1stDTO.setDiabetesDrug(false);
        }

        Long currentId = BaseContext.getCurrentId(); //用户ID
        //更新user_info
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        UserInfo existingUser = userInfoMapper.selectById(currentId);
        if (existingUser != null) {
            String pwd = existingUser.getPhone();
            pwd = pwd.substring(pwd.length() - 6);
            userInfoDTO.setPassword(pwd);
        }
        userInfoDTO.setId(currentId);
        BeanUtils.copyProperties(userHome1stDTO, userInfoDTO);
        userInfoMapper.updateUserInfo(userInfoDTO);
        //更新user_health
        UserHealth userHealth = new UserHealth();
        BeanUtils.copyProperties(userHome1stDTO, userHealth);
        userHealthService.saveUserHealth(userHealth);

        //更新user_eval
        UserEval userEval = new UserEval();
        userEval.setEvaluation(userHome1stDTO.getEvaluation());
        userEvalService.save(userEval);

        //更新user_propolis
        UserPropolis userPropolis = new UserPropolis();
        BeanUtils.copyProperties(userHome1stDTO, userPropolis);
        userPropolisService.add(userPropolis);

        //更新user_checkup_form
        if (userHome1stDTO.getPicUrl() != "" && userHome1stDTO.getPicUrl() != null) {
            //更新user_checkup_form
            UserCheckupForm userCheckupForm = new UserCheckupForm();
            userCheckupForm.setUserId(currentId);
            userCheckupForm.setPicUrl(userHome1stDTO.getPicUrl());
            userCheckupForm.setUpdateTime(LocalDateTime.now());
            userCheckupFormService.add(userCheckupForm);
        }
    }

    @Override
    @Transactional
    public void saveQuestionnaires(UserHomeDTO userHomeDTO) {
        Long currentId = BaseContext.getCurrentId(); //用户ID
        //更新user_eval
        UserEval userEval = new UserEval();
        userEval.setEvaluation(userHomeDTO.getEvaluation());
        userEvalService.save(userEval);

        //更新user_propolis
        UserPropolis userPropolis = new UserPropolis();
        BeanUtils.copyProperties(userHomeDTO, userPropolis);
        userPropolisService.add(userPropolis);

        if (userHomeDTO.getPicUrl() != "" && userHomeDTO.getPicUrl() != null) {
            //更新user_checkup_form
            UserCheckupForm userCheckupForm = new UserCheckupForm();
            userCheckupForm.setUserId(currentId);
            userCheckupForm.setPicUrl(userHomeDTO.getPicUrl());
            userCheckupForm.setUpdateTime(LocalDateTime.now());
            userCheckupFormService.add(userCheckupForm);
        }

    }
}
