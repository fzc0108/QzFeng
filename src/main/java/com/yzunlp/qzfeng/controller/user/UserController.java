package com.yzunlp.qzfeng.controller.user;

import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.dto.RegisterDTO;
import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.dto.UserHomeDTO;
import com.yzunlp.qzfeng.service.UserEvalService;
import com.yzunlp.qzfeng.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 10297
 * @since 2025/7/10 15:30
 */
@Slf4j
@RestController
@RequestMapping("/user/all")
@Tag(name = "user-all接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "用户第一次")
    @PostMapping("/saveAll")
    public Result<Void> saveAll(@RequestBody UserHome1stDTO userHome1stDTO) {
        log.info("用户第一次: {}", userHome1stDTO);
        userService.saveAll(userHome1stDTO);
        return Result.success();
    }

    @Operation(summary = "用户后续")
    @PostMapping("/saveQuestionnaires")
    public Result<Void> saveQuestionnaires(@RequestBody UserHomeDTO userHomeDTO) {
        log.info("用户后续: {}", userHomeDTO);
        userService.saveQuestionnaires(userHomeDTO);
        return Result.success();
    }
}
