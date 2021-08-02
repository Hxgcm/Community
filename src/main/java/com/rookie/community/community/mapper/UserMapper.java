package com.rookie.community.community.mapper;

import com.rookie.community.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author HeXianGang
 * @description 用户表数据库持久层
 * @create 2021-07-13 21:26
 */
@Mapper
public interface UserMapper {

    /**
     * 向User表中插入数据
     */
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified) values (#{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified})")
    public void insertUser(User user);

    /**
     * 根据token查找用户的信息
     * @param token
     * @return
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
