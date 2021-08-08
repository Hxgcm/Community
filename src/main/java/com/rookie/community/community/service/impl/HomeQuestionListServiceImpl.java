package com.rookie.community.community.service.impl;

import com.rookie.community.community.dto.QuestionsDTO;
import com.rookie.community.community.mapper.QuestionMapper;
import com.rookie.community.community.mapper.UserMapper;
import com.rookie.community.community.pojo.Question;
import com.rookie.community.community.pojo.User;
import com.rookie.community.community.service.HomeQuestionListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXianGang
 * @description 首页的问题列表业务的实现类
 * @create 2021-08-08 20:02
 */
@Service
public class HomeQuestionListServiceImpl  implements HomeQuestionListService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<QuestionsDTO> disposeQuestionsDTO() {
        //要返回的QuestionsDTO集合
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        //question表中的所有问题列表
        List<Question> questions = questionMapper.selectAll();
        for (Question question : questions) {
            QuestionsDTO questionsDTO = new QuestionsDTO();
            User user = userMapper.selectUserById(question.getCreator());
            BeanUtils.copyProperties(question, questionsDTO);
            // 组装questionDTO
            questionsDTO.setUser(user);
            questionsDTOS.add(questionsDTO);
        }
        return questionsDTOS;
    }
}
