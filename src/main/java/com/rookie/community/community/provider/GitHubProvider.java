package com.rookie.community.community.provider;


import com.alibaba.fastjson.JSON;
import com.rookie.community.community.dto.GitHubUser;
import com.rookie.community.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;

/**
 * @author HeXianGang
 * @description GitHub登录提供服务
 * @create 2021-07-03 23:01
 */
@Component
public class GitHubProvider {

    /**
     * 获取AccessToken
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                                     .url("https://github.com/login/oauth/access_token")
                                     .post(body)
                                     .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println(result);
            System.out.println(result.split("&")[0].split("=")[1]);
            return result.split("&")[0].split("=")[1];
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取GitHub对象
     * @param accessToken
     * @return
     */
    public GitHubUser getGitHubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.github.com/user").header("Authorization", "token " + accessToken).build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
            return JSON.parseObject(result, GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
