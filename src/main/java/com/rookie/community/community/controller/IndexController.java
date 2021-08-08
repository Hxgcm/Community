package com.rookie.community.community.controller;

import com.rookie.community.community.dto.QuestionsDTO;
import com.rookie.community.community.mapper.UserMapper;
import com.rookie.community.community.pojo.User;
import com.rookie.community.community.service.HomeQuestionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HeXianGang
 * @description 首页Controller类
 * @create 2021-07-01 21:47
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private HomeQuestionListService homeQuestionListService;

    @GetMapping("/")
    public String indexRoute(HttpServletRequest request,
                             Model model) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null ) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        // 将组装好的QuestionsDTOS返回给前端
        List<QuestionsDTO> questionsDTOS = homeQuestionListService.disposeQuestionsDTO();
        model.addAttribute("questions", questionsDTOS);

        return "index";
    }
}
