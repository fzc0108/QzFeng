package com.yzunlp.qzfeng.controller.user;

import com.yzunlp.qzfeng.domain.dto.UserInfoDTO;
import com.yzunlp.qzfeng.service.UserInfoService;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.dto.LoginDTO;
import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.po.UserInfo;
import com.yzunlp.qzfeng.domain.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * @author: Huang
 * @description: 用户接口 Controller
 */
@Slf4j
@RestController
@RequestMapping("/user/info")
@Tag(name = "user-info接口")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userService) {
        this.userInfoService = userService;
    }

    @Operation(summary = "用户注册并自动登录")
    @PostMapping("/register")
    public Result<LoginVO> register(@RequestBody @Valid UserHome1stDTO userHome1stDTO) {
        log.info("用户注册提交: {}", userHome1stDTO);
        LoginVO loginVO = userInfoService.register(userHome1stDTO);
        return Result.success(loginVO);
    }

    @Operation(summary = "修改用户信息")
    @PutMapping
    public Result<Void> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        log.info("用户修改信息: {}", userInfoDTO);
        userInfoService.updateUserInfo(userInfoDTO);
        return Result.success();
    }

    @Operation(summary = "根据id查找用户信息")
    @PostMapping
    public Result<UserInfo> selectById() {
        log.info("根据id查找用户信息");
        UserInfo userInfo = userInfoService.selectById();
        return Result.success(userInfo);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.info("用户登录 request: {}", loginDTO);
        LoginVO loginVO = userInfoService.login(loginDTO);
        // 调用 Service：
        // 1. 检查是否存在 -> 自动注册
        // 2. 校验 userCode
        // 3. 生成 Token
        if (loginVO == null) {
            log.info("用户未注册，返回 202 状态码引导跳转");

        // 直接返回结果，包含 token, userCode, isNewUser
            Result<LoginVO> result = Result.error("用户未注册，请前往填写信息");
            result.setCode(202); // 强制设置状态码为 202
            return result;
        }
        return Result.success(loginVO);
    }
}