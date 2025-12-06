package com.yzunlp.qzfeng.controller.admin;

import com.yzunlp.qzfeng.common.PageResult;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.vo.UserInfoHealthVO;
import com.yzunlp.qzfeng.domain.vo.UserQuestionnaireVO;
import com.yzunlp.qzfeng.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * @author 10297
 * @since 2025/7/1 14:47
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Tag(name = "admin接口")
public class
AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 增加管理员登陆，采用session登陆方式
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<Map<String, String>> login(@RequestParam String username,
                                             @RequestParam String password,
                                             HttpSession session) {
        log.info("管理员尝试登录: {}", username);

        if ("admin".equals(username) && "admin312".equals(password)) {


            session.setAttribute("ADMIN_SESSION", username);

            Map<String, String> map = new HashMap<>();
            map.put("redirectUrl", "/admin/home3.html");

            return Result.success(map);
        } else {
            return Result.error("管理员账号或密码错误");
        }
    }

    @GetMapping("/logout")
    @Operation(summary = "管理员退出")
    public Result<String> logout(HttpSession session) {
        session.invalidate(); // 清除 Session
        return Result.success("退出成功");
    }

    @GetMapping("/userList")
    @Operation(summary = "查询（分页）全部用户")
    public Result<PageResult> getAllUsers(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        log.info("查询（分页）全部用户");
        return Result.success(adminService.getAllUsers(pageNum, pageSize));
    }
    @GetMapping("/getUserInfoHealthById/{id}")
    @Operation(summary = "查询用户信息与健康信息")
    public Result<UserInfoHealthVO> selectUserInfoHealthById(@PathVariable Long id) {
        log.info("查询用户信息与健康信息(user-id={})",  id);
        return Result.success(adminService.selectUserInfoHealthById(id));
    }

    @GetMapping("/getUserQuestionnaireById/{id}")
    @Operation(summary = "查询用户所有填写过的问卷信息")
    public Result<UserQuestionnaireVO> selectUserQuestionnaireById(@PathVariable Long id) {
        log.info("查询用户所有填写过的问卷信息(user-id={})",  id);
        return Result.success(adminService.selectUserQuestionnaireById(id));
    }

}
