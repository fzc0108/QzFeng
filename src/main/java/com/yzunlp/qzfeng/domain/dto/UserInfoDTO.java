package com.yzunlp.qzfeng.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 10297
 * @since 2025/7/1 22:17
 */
@Data
public class UserInfoDTO {
    private Long id; // 用户的唯一标识码
    private String name; // 用户的姓名
    private String password; // 用户的密码
    private String chinaId; // 用户的身份证号
    private Date birthday; // 用户的生日
    private Short sex; // 用户的性别，男1，女2
    private Long areaCode; // 用户所在地区编码


    public UserInfoDTO() {
    }

    public UserInfoDTO(Long id, String name, String password, String chinaId, Date birthday, Short sex, Long areaCode) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.chinaId = chinaId;
        this.birthday = birthday;
        this.sex = sex;
        this.areaCode = areaCode;

    }
}
