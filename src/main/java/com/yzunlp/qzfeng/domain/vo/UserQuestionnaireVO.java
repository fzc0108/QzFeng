package com.yzunlp.qzfeng.domain.vo;

import com.yzunlp.qzfeng.domain.po.UserCheckupForm;
import com.yzunlp.qzfeng.domain.po.UserEval;
import com.yzunlp.qzfeng.domain.po.UserPropolis;
import lombok.Data;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/2 13:22
 */
@Data
public class UserQuestionnaireVO {
    private List<UserPropolis> userPropolisList;
    private List<UserEval> userEvalList;
    private List<UserCheckupForm> userCheckupFormList;

    public UserQuestionnaireVO() {
    }

    public UserQuestionnaireVO(
            List<UserPropolis> userPropolisList,
            List<UserEval> userEvalListerEval,
            List<UserCheckupForm> userCheckupFormList
    ) {
        this.userPropolisList = userPropolisList;
        this.userEvalList = userEvalListerEval;
        this.userCheckupFormList = userCheckupFormList;
    }
}
