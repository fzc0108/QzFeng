package com.yzunlp.qzfeng.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/**
 * @author: Huang
 * @description: TODO
 * @date: 2025/6/28 15:14
 */
@Data
public class RegisterDTO {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone; // 用户的电话，唯一
    @NotBlank(message = "姓名不能为空")
    private String name; // 用户的姓名

    private String password = "yzunlp"; // 用户的密码
    private String chinaId; // 用户的身份证号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday; // 用户的生日
    private Short sex; // 用户的性别，男1，女2
    private Long areaCode; // 用户所在地区编码
    private String userCode;

    public RegisterDTO() {
    }

    public RegisterDTO(String phone, String name, String password, String chinaId, Date birthday, Short sex, Long areaCode, String userCode) {
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.chinaId = chinaId;
        this.birthday = birthday;
        this.sex = sex;
        this.areaCode = areaCode;
        this.userCode = userCode;
    }
}
