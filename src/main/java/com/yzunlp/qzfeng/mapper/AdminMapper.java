package com.yzunlp.qzfeng.mapper;

import com.github.pagehelper.Page;
import com.yzunlp.qzfeng.domain.po.UserCheckupForm;
import com.yzunlp.qzfeng.domain.po.UserEval;
import com.yzunlp.qzfeng.domain.po.UserPropolis;
import com.yzunlp.qzfeng.domain.vo.UserInfoBaseVO;
import com.yzunlp.qzfeng.domain.vo.UserInfoHealthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    UserInfoHealthVO selectUserInfoHealthById(Long id);

    List<UserPropolis> selectUserPropolisByUserId(Long id);

    List<UserEval> selectUserEvalByUserId(Long id);

    List<UserCheckupForm> selectUserCheckupFormByUserId(Long id);

    Page<UserInfoBaseVO> selectAllUsers();
}
