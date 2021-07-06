package com.rookie.community.community.controller;

import com.rookie.community.community.dto.AccessTokenDTO;
import com.rookie.community.community.dto.GitHubUser;
import com.rookie.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.SocketAddress;

/**
 * @author HeXianGang
 * @description github登录注册
 * @create 2021-07-03 22:24
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam( name = "code")String code,
                           @RequestParam( name = "state")String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("a157e57e786fbbc1e8db");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_secret("e62c0cf2411766f8f256e98204bc964c41533f9b");
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getGitHubUser(accessToken);
        System.out.println(gitHubUser.getName());
        return "index";
    }





}
