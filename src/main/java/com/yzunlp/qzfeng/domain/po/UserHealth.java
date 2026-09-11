package com.yzunlp.qzfeng.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserHealth implements Serializable {
    private Long id;

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
}
