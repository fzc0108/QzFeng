package com.yzunlp.qzfeng.mapper;

import com.yzunlp.qzfeng.domain.dto.FrequencyCountDTO;
import com.yzunlp.qzfeng.domain.dto.YearCountDTO;
import com.yzunlp.qzfeng.domain.po.UserPropolis;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/1 16:24
 */
@Mapper
public interface UserPropolisMapper {
    /**
     * 添加用户蜂胶使用情况
     *
     * @param userPropolis 用户蜂胶信息
     */
    void addUserPropolis(UserPropolis userPropolis);

    /**
     * 修改用户蜂胶使用情况
     *
     * @param userPropolis 用户蜂胶信息
     */
    void updateUserPropolis(UserPropolis userPropolis);

    /**
     * 查询用户的蜂胶使用情况
     *
     * @param userId 用户ID
     * @return 用户蜂胶信息
     */
    UserPropolis selectUserPropolis(Long userId);

    YesNoVO propolisYesOrNo();

    List<YearCountDTO> countPropolisByYear();

    List<FrequencyCountDTO> countPropolisByFrequency();
}
