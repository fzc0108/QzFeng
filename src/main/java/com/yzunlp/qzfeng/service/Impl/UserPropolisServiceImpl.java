package com.yzunlp.qzfeng.service.Impl;

import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.Result;
import com.yzunlp.qzfeng.domain.dto.FrequencyCountDTO;
import com.yzunlp.qzfeng.domain.dto.YearCountDTO;
import com.yzunlp.qzfeng.domain.po.UserPropolis;
import com.yzunlp.qzfeng.domain.vo.YearPeopleVO;
import com.yzunlp.qzfeng.domain.vo.YesNoVO;
import com.yzunlp.qzfeng.mapper.UserPropolisMapper;
import com.yzunlp.qzfeng.service.UserPropolisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 10297
 * @since 2025/7/1 16:42
 */
@Slf4j
@Service
public class UserPropolisServiceImpl implements UserPropolisService {

    @Autowired
    private UserPropolisMapper userPropolisMapper;

    @Override
    public void add(UserPropolis userPropolis) {
        // 从上下文(ThreadLocal)获取用户ID
        userPropolis.setUserId(BaseContext.getCurrentId());
        log.info("用户ID: {}", userPropolis.getUserId());
        // 设置默认更新时间
        if (userPropolis.getUpdateTime() == null) {
            userPropolis.setUpdateTime(LocalDateTime.now());
        }
        userPropolisMapper.addUserPropolis(userPropolis);
    }

    @Override
    public void update(UserPropolis userPropolis) {
        // 自动更新时间
        userPropolis.setUpdateTime(LocalDateTime.now());
        userPropolisMapper.updateUserPropolis(userPropolis);
    }

    @Override
    public UserPropolis getByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            userId = BaseContext.getCurrentId();
        }
        log.info("service: 查询 用户id={} 的蜂胶信息", userId);
        return userPropolisMapper.selectUserPropolis(userId);
    }

    @Override
    public YesNoVO propolisYesOrNo() {
//        YesNoVO yesNoVO = new YesNoVO();
//        yesNoVO.setYes(2);
//        yesNoVO.setNo(58);
        return userPropolisMapper.propolisYesOrNo();
    }

    @Override
    public YearPeopleVO propolisYear() {
        // 1. 从Mapper获取按年分组的原始统计数据
        List<YearCountDTO> yearCounts = userPropolisMapper.countPropolisByYear();

        // 2. 创建最终要返回的VO对象和它内部的两个列表
        YearPeopleVO resultVO = new YearPeopleVO();
        List<Integer> yearList = new ArrayList<>();
        List<Integer> peopleList = new ArrayList<>();

        // 3. 遍历DTO列表，将数据拆分填充到VO的两个列表中
        for (YearCountDTO dto : yearCounts) {
            yearList.add(dto.getYear());
            peopleList.add(dto.getPeople());
        }
        // 4. 将填充好的列表设置到VO对象中
        resultVO.setYear(yearList);
        resultVO.setPeople(peopleList);
        // 5. 返回填充好的VO对象
        return resultVO;
    }

    @Override
    public Map<String, Integer> propolisFrequency() {
        // 1. 从Mapper获取原始的统计数据列表
        List<FrequencyCountDTO> frequencyCounts = userPropolisMapper.countPropolisByFrequency();
        Map<String, Integer> resultMap = frequencyCounts.stream()
                .collect(Collectors.toMap(
                        FrequencyCountDTO::getFrequency,  // Map的Key
                        FrequencyCountDTO::getCount       // Map的Value
                ));
        // 3. 返回最终的Map
        return resultMap;
    }
}
