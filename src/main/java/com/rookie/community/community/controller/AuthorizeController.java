package com.rookie.community.community.controller;

import com.rookie.community.community.dto.AccessTokenDTO;
import com.rookie.community.community.dto.GitHubUser;
import com.rookie.community.community.mapper.UserMapper;
import com.rookie.community.community.pojo.User;
import com.rookie.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.SocketAddress;
import java.util.UUID;

/**
 * @author HeXianGang
 * @description github登录注册
 * @create 2021-07-03 22:24
 */
@Controller
@PropertySource(value = "gitHubResource.properties")
public class AuthorizeController {

    @Value("${github.client.id}")
    private String id;

    @Value("${github.client.secret}")
    private String secret;

    @Value("${github.redirect.url}")
    private String url;

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam( name = "code")String code,
                           @RequestParam( name = "state")String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(id);
        accessTokenDTO.setRedirect_uri(url);
        accessTokenDTO.setClient_secret(secret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getGitHubUser(accessToken);
        System.out.println(gitHubUser.getName());
        if (gitHubUser.getId() != null) {
            // 如果用户不为空，则登录成功
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }





}
