package com.yzunlp.qzfeng.mapper;

import com.yzunlp.qzfeng.domain.dto.YearCountDTO;
import com.yzunlp.qzfeng.domain.po.Info2Health;
import com.yzunlp.qzfeng.domain.po.UserHealth;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 10297
 * @since 2025/7/1 16:03
 */
@Mapper
public interface UserHealthMapper {
    /**
     * 添加用户健康信息
     *
     * @param userHealth 用户健康信息
     */
    void addUserHealth(UserHealth userHealth);

    /**
     * 修改用户健康信息
     *
     * @param userHealth 用户健康信息
     */
    void updateUserHealth(UserHealth userHealth);

    /**
     * 查询用户健康信息
     *
     * @param userId 用户ID
     * @return 用户健康信息
     */
    UserHealth selectUserHealthByUserId(Long userId);

    void addInfo2Health(Info2Health info2Health);

    Info2Health selectInfo2Health(Long userId);

    YesNoVO searchYesOrNo(String disease);

    List<YearCountDTO> hypertensionYear(String disease);

    YesNoVO searchDrugYesOrNo(String disease);
}
