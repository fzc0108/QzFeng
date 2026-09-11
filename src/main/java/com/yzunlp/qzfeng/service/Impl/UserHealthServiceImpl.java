package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.dto.YearCountDTO;
import com.yzunlp.qzfeng.domain.po.Info2Health;
import com.yzunlp.qzfeng.domain.po.UserHealth;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;
import com.yzunlp.qzfeng.mapper.UserHealthMapper;
import com.yzunlp.qzfeng.service.UserHealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 10297
 * @since 2025/7/1 22:06
 */
@Slf4j
@Service
public class UserHealthServiceImpl implements UserHealthService {

    private final UserHealthMapper userHealthMapper;
    @Autowired
    public UserHealthServiceImpl(UserHealthMapper userHealthMapper) {
        this.userHealthMapper = userHealthMapper;
    }

    /**
     * 保存用户健康信息（如果有就更新）
     * @param userHealth
     */
//    @Override
//    public void saveUserHealth(UserHealth userHealth) {
//        // 查寻是否存在
//        Info2Health info2Health = userHealthMapper.selectInfo2Health(BaseContext.getCurrentId());
//        // 如果不存在就插入
//        if (info2Health == null) {
//            // 保存用户健康信息
//            userHealthMapper.addUserHealth(userHealth);
//            info2Health = new Info2Health();
//            info2Health.setInfoId(BaseContext.getCurrentId());
//            info2Health.setHealthId(userHealth.getId());
//            // 保存“用户信息-用户健康信息”映射
//            userHealthMapper.addInfo2Health(info2Health);
//        } else {
//            // 如果存在就更新
//            userHealth.setId(info2Health.getHealthId());
//            userHealthMapper.updateUserHealth(userHealth);
//        }
//    }

    /**
     * 保存用户健康信息（带事务控制）
     */
//    @Transactional(rollbackFor = Exception.class) // 开启事务，并在异常时回滚
    public void saveUserHealth(UserHealth userHealth) {
        // 获取当前用户ID
        Long userId = BaseContext.getCurrentId();

        // 查询是否存在映射关系
        Info2Health info2Health = userHealthMapper.selectInfo2Health(userId);

        if (info2Health == null) {
            // 1. 插入健康信息
            userHealthMapper.addUserHealth(userHealth);

            // 2. 获取刚插入的健康信息ID（假设数据库主键自增，userHealth.getId() 可以拿到）
            Long healthId = userHealth.getId();
            if (healthId == null) {
                throw new RuntimeException("健康信息插入失败，无法获取ID");
            }

            // 3. 创建映射关系
            info2Health = new Info2Health();
            info2Health.setInfoId(userId);
            info2Health.setHealthId(healthId);

            // 4. 插入映射关系
            userHealthMapper.addInfo2Health(info2Health);
        } else {
            // 如果存在就更新
            userHealth.setId(info2Health.getHealthId());
            userHealthMapper.updateUserHealth(userHealth);
        }
    }

    @Override
    public UserHealth getUserHealthById(Long userId) {
        if (userId == null || userId <= 0) {
            userId = BaseContext.getCurrentId();
        }
        return userHealthMapper.selectUserHealthByUserId(userId);
    }

    @Override
    public Result<YesNoVO> hypertension(String disease) {
        return Result.success(userHealthMapper.searchYesOrNo(disease));
    }

    @Override
    public Result<YearPeopleVO> hypertensionYear(String disease) {
//         1. 从 Mapper 获取按年分组的统计数据列表
        List<YearCountDTO> yearCounts = userHealthMapper.hypertensionYear(disease);
        // 2. 创建最终的 VO 对象和内部的列表
        YearPeopleVO resultVO = new YearPeopleVO();
        List<Integer> yearList = new ArrayList<>();
        List<Integer> peopleList = new ArrayList<>();

        // 3. 遍历 DTO 列表，将数据拆分到 VO 的两个列表中
        for (YearCountDTO dto : yearCounts) {
            yearList.add(dto.getYear());
            peopleList.add(dto.getPeople());
        }
        // 4. 将填充好的列表设置到 VO 对象中
        resultVO.setYear(yearList);
        resultVO.setPeople(peopleList);

        // 5. 返回成功的结果
        return Result.success(resultVO);
    }

    @Override
    public Result<YesNoVO> hypertensionDrug(String disease) {
        return Result.success(userHealthMapper.searchDrugYesOrNo(disease));
    }
}
