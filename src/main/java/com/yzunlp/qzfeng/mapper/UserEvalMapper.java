package com.yzunlp.qzfeng.mapper;

import com.yzunlp.qzfeng.domain.dto.EvaluationCountDTO;
import com.yzunlp.qzfeng.domain.po.UserEval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/1 16:26
 */
@Mapper
public interface UserEvalMapper {
    /**
     * 添加用户主观评价
     *
     * @param userEval 用户主观评价
     */
    void addUserEval(UserEval userEval);

    /**
     * 修改用户主观评价
     *
     * @param userEval 用户主观评价
     */
    void updateUserEval(UserEval userEval);

    /**
     * 查询用户主观评价
     *
     * @param userId 用户ID
     * @return 用户主观评价
     */
    UserEval selectUserEval(Long userId);
    // 根据问题索引(eid)统计分数分布
    List<EvaluationCountDTO> countScoresByEvaluationIndex(@Param("eid") Integer eid);
}
