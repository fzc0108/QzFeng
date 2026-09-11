package com.yzunlp.qzfeng.controller.user;

import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.po.UserHealth;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;
import com.yzunlp.qzfeng.service.UserHealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/1 22:09
 */
@Tag(name = "user-health接口")
@Slf4j
@RestController
@RequestMapping("/user/health")
public class UserHealthController {

    private final UserHealthService userHealthService;

    @Autowired
    public UserHealthController(UserHealthService userHealthService) {
        this.userHealthService = userHealthService;
    }

    @Operation(summary = "用户填写(更新)健康信息")
    @PostMapping("/update")
    public Result<Void> saveUserHealth(@RequestBody UserHealth userHealth) {
        log.info("用户填写健康信息: {}", userHealth);
        userHealthService.saveUserHealth(userHealth);
        return Result.success();
    }

    @Operation(summary = "根据id查找用户健康信息")
    @GetMapping("/{id}")
    public Result<UserHealth> getUserHealth(@PathVariable("id") Long id) {
        log.info("根据id查找用户健康信息");
        UserHealth userHealth = userHealthService.getUserHealthById(id);
        return Result.success(userHealth);
    }

    @Operation(summary = "统计用户健康信息(有病吗？)")
    @GetMapping("/admin/{disease}/yes-no")
    public Result<YesNoVO> hypertension(@PathVariable("disease") String disease) {
        log.info("统计用户健康信息({})", disease);
        return userHealthService.hypertension(disease);
    }

    @Operation(summary = "统计用户健康信息(病了几年？)")
    @GetMapping("/admin/{disease}/year")
    public Result<YearPeopleVO> hypertensionYear(@PathVariable("disease") String disease) {
        log.info("统计用户健康信息({}-year)", disease);
        return userHealthService.hypertensionYear(disease);
    }

    @Operation(summary = "统计用户健康信息(喝了几年药？)")
    @GetMapping("/admin/{disease}/drug")
    public Result<YesNoVO> hypertensionDrug(@PathVariable("disease") String disease) {
        log.info("统计用户健康信息({}-drug)", disease);
        return userHealthService.hypertensionDrug(disease);
    }
}
