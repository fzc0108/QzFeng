package com.yzunlp.qzfeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 10297
 * @since 2025/7/10 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHome1stDTO   {
    @NotBlank(message = "姓名不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5·]+$", message = "姓名必须是纯中文")
    private String name; // 用户的姓名

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone; // 用户的电话，唯一

    private String userCode; // 用户编码
    private String chinaId; // 用户的身份证号

    @NotNull(message = "出生日期不能为空")
    @Past(message = "出生日期必须是过去的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    private Boolean propolis; // 是否服用过蜂胶
    private Short propolisYear; // 从何时开始使用蜂胶
    private String propolisFrequency; // 蜂胶使用频率(单选: ABCD)

    private String evaluation; // 使用逗号分割，共六个评分(评分范围[0, 3])

    private String picUrl; // 体检单(图片)地址
}
