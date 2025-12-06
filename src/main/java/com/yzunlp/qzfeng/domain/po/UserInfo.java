package com.yzunlp.qzfeng.domain.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author 10297
 * @since 2025/6/25 23:16
 */
@Data
@Builder
public class UserInfo {
    private Long id; // 用户的唯一标识码
    private String phone; // 用户的电话，唯一
    private String name = "户晨风"; // 用户的姓名
    private String password = "yzunlp"; // 用户的密码
    private String chinaId; // 用户的身份证号
    private Date birthday; // 用户的生日
    private Short sex; // 用户的性别，男1，女2
    private Long areaCode; // 用户所在地区编码
    private String userCode;// 用户的编号

    public UserInfo() {
    }

    public UserInfo(Long id, String phone, String name, String password, String chinaId, Date birthday, Short sex, Long areaCode, String userCode) {
        this.id = id;
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
