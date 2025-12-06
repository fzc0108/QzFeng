package com.yzunlp.qzfeng.controller.user;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.common.UploadProperties;
import com.yzunlp.qzfeng.domain.po.UserCheckupForm;
import com.yzunlp.qzfeng.service.UserCheckupFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 10297
 * @since 2025/7/1 16:57
 */
@Tag(name = "user-checkup接口")
@Slf4j
@RestController
@RequestMapping("/user/checkup")
public class UserCheckupFormController {

    private static final String UPLOAD_DIR = "/data/uploads/";

    private final UploadProperties uploadProperties;
    private final UserCheckupFormService checkupFormService;

    @Autowired
    public UserCheckupFormController(UploadProperties uploadProperties, UserCheckupFormService checkupFormService) {
        this.uploadProperties = uploadProperties;
        this.checkupFormService = checkupFormService;
    }

    /**
     * 添加体检单图片地址
     */
    @Operation(summary = "用户上传体检报告（图片）")
    @PostMapping("/uploads")
    public Result<UserCheckupForm> addCheckupForm(@RequestParam("file") MultipartFile file) {
        log.info("用户上传图片(user-id={})", BaseContext.getCurrentId());
        if (file.isEmpty()) {
            return Result.error("上传失败:文件为空");
        }
        // 检查是否为图片
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                return Result.error("上传失败:文件不是有效图片");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败:读取文件错误");
        }

        // 获取 MIME 类型
        String contentType = file.getContentType();
//        log.info("图片MIME类型: {}", contentType);
        String extension = "img";
        if (contentType != null) {
            extension = contentType.substring(contentType.lastIndexOf("/") + 1).toLowerCase();
//            log.info("图片扩展名: {}", extension);
        }

        String fileName = "UID_" + BaseContext.getCurrentId().toString() + "_" + UUID.randomUUID() + "." + extension;
        try {
            file.transferTo(new File(uploadProperties.getUser_upload_path() + fileName));
            log.info("图片保存地址: {}", uploadProperties.getUser_upload_path() + fileName);
            UserCheckupForm userCheckupForm = new UserCheckupForm();
            userCheckupForm.setUserId(BaseContext.getCurrentId());
            userCheckupForm.setPicUrl(fileName);
            checkupFormService.add(userCheckupForm);
            return Result.success(userCheckupForm);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败，保存文件出错");
        }

    }

    /**
     * 更新体检单图片地址
     */
    @PutMapping
    public Result<Void> updateCheckupForm(@RequestBody UserCheckupForm checkupForm) {
        checkupFormService.update(checkupForm);
        return Result.success(null);
    }

    /**
     * 查询用户的体检单信息
     */
    @GetMapping("/{userId}")
    public Result<UserCheckupForm> getCheckupForm(@PathVariable Long userId) {
        UserCheckupForm form = checkupFormService.getByUserId(userId);
        return Result.success(form);
    }
}
