package com.yzunlp.qzfeng.service;

import com.yzunlp.qzfeng.domain.dto.UserHome1stDTO;
import com.yzunlp.qzfeng.domain.dto.UserHomeDTO;

public interface UserService {
    void saveAll(UserHome1stDTO userHome1stDTO);

    void saveQuestionnaires(UserHomeDTO userHomeDTO);
}
