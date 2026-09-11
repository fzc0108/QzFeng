package com.yzunlp.qzfeng.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 10297
 * @since 2025/7/2 13:07
 */
@Data
public class UserInfoHealthVO {
    private Long id; // 用户的唯一标识码
    private String phone; // 用户的电话，唯一
    private String name; // 用户的姓名
    private String chinaId; // 用户的身份证号
    private Date birthday; // 用户的生日
    private Short sex; // 用户的性别，男1，女2
    private Long areaCode; // 用户所在地区编码

    private Boolean hypertension; // 是否有高血压
    private Integer hypertensionYear; // 高血压病程几年
    private Boolean hypertensionDrug; // 是否规律服药

    private Boolean diabetes; // 是否有糖尿病
    private Integer diabetesYear; // 糖尿病病程几年
    private Boolean diabetesDrug; // 是否规律服药

    private Boolean hyperlipidemia; // 是否有高血脂
    private Integer hyperlipidemiaYear; // 高血脂病程几年
    private Boolean hyperlipidemiaDrug; // 是否规律服药

    private Boolean tumor; // 是否有肿瘤

    public UserInfoHealthVO() {
    }

    public UserInfoHealthVO(
            Long id, String phone, String name,
            String chinaId, Date birthday, Short sex, Long areaCode,
            Boolean hypertension, Integer hypertensionYear, Boolean hypertensionDrug,
            Boolean diabetes, Integer diabetesYear, Boolean diabetesDrug,
            Boolean hyperlipidemia, Integer hyperlipidemiaYear, Boolean hyperlipidemiaDrug,
            Boolean tumor
    ) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.chinaId = chinaId;
        this.birthday = birthday;
        this.sex = sex;
        this.areaCode = areaCode;
        this.hypertension = hypertension;
        this.hypertensionYear = hypertensionYear;
        this.hypertensionDrug = hypertensionDrug;
        this.diabetes = diabetes;
        this.diabetesYear = diabetesYear;
        this.diabetesDrug = diabetesDrug;
        this.hyperlipidemia = hyperlipidemia;
        this.hyperlipidemiaYear = hyperlipidemiaYear;
        this.hyperlipidemiaDrug = hyperlipidemiaDrug;
        this.tumor = tumor;
    }
}
