package com.yzunlp.qzfeng.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/**
 * @author: Huang
 * @description: TODO
 * @date: 2025/6/28 14:42
 */
@Data
public class LoginDTO {

    //添加非空校验和正则校验
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;
    private String password;
    private String userCode;

}
