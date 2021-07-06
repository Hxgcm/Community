package com.rookie.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author HeXianGang
 * @description 首页Controller类
 * @create 2021-07-01 21:47
 */

@Controller
public class IndexController {


    @GetMapping("/")
    public String indexRoute() {
        return "index";
    }
}
