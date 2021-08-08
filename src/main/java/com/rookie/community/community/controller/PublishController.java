package com.rookie.community.community.controller;

import com.rookie.community.community.mapper.QuestionMapper;
import com.rookie.community.community.mapper.UserMapper;
import com.rookie.community.community.pojo.Question;
import com.rookie.community.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.TileObserver;

/**
 * @author HeXianGang
 * @description 发起问题的Handler
 * @create 2021-08-02 21:22
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    /**
     * 发布问题 处理请求函数
     * @param request
     * @param model
     * @param title
     * @param description
     * @param tag
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(
            HttpServletRequest request,
            Model model,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag ) {
        // 将前台页面输入的信息重新传输给前台
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        // 增加判断，判断标题，描述，内容 是否为空
        if("".equals(title) || title == null) {
            model.addAttribute("error", "请填写标题！");
            return "publish";
        }
        if("".equals(description) || description == null) {
            model.addAttribute("error", "请填写描述！");
            return "publish";
        }
        if("".equals(tag) || tag == null) {
            model.addAttribute("error", "请填写标签！");
            return "publish";
        }
        // 读取Cookie获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.createQuestion(question);

        return "redirect:/";
    }

}
