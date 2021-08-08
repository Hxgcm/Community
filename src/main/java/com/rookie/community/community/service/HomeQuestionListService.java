package com.rookie.community.community.service;

import com.rookie.community.community.dto.QuestionsDTO;

import java.util.List;

/**
 * @author HeXianGang
 * @description 首页的问题列表业务
 * @create 2021-08-08 19:59
 */
public interface HomeQuestionListService {

    /**
     * 组装QuestionsDTO对象集合
     * @return
     */
    public List<QuestionsDTO> disposeQuestionsDTO();
}
