package com.yzunlp.qzfeng.controller.user;

import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.po.UserEval;
import com.yzunlp.qzfeng.service.UserEvalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * @author 10297
 * @since 2025/7/1 16:57
 */
@Tag(name = "user-eval接口")
@Slf4j
@RestController
@RequestMapping("/user/eval")
public class UserEvalController {

    @Autowired
    private UserEvalService userEvalService;

    /**
     * 添加主观评价
     */
    @Operation(summary = "用户填写蜂胶使用主观体验")
    @PostMapping
    public Result<Void> addUserEval(@RequestBody UserEval userEval) {
        log.info("用户填写蜂胶使用主观体验: {}", userEval);
        userEvalService.save(userEval);
        return Result.success(null);
    }

    /**
     * 更新主观评价
     */
    @PutMapping
    public Result<Void> updateUserEval(@RequestBody UserEval userEval) {
        log.info("用户修改蜂胶使用主观体验: {}", userEval);
        userEvalService.update(userEval);
        return Result.success(null);
    }

    /**
     * 查询用户的主观评价
     */
    @GetMapping("/{userId}")
    public Result<UserEval> getUserEval(@PathVariable("userId") Long userId) {
        log.info("查询用户的蜂胶使用主观体验");
        UserEval eval = userEvalService.getByUserId(userId);
        return Result.success(eval);
    }

    @Operation(summary = "admin:查询用户主观体验")
    @GetMapping("/admin/{eid}")
    public Result<Map<Integer, Integer>> queryScoresByEid(@PathVariable("eid") Integer eid) {
//        log.debug("admin:查询用户主观体验(eid={})", eid);
//        // todo Map的 K 只能为 0，1，2
//        Map<Integer, Integer> r = Map.of(
//                0, 22,
//                1, 5,
//                2, 13);
//        return Result.success(r);
        if (eid == null || eid < 0 || eid > 5) {
            return Result.success(Collections.emptyMap());
        }
        return Result.success(userEvalService.countScoresByEvaluationIndex(eid));
    }

}
