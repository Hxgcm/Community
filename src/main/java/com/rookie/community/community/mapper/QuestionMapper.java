package com.rookie.community.community.mapper;

import com.rookie.community.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HeXianGang
 * @description 提出问题的持久层
 * @create 2021-08-08 9:58
 */
@Mapper
public interface QuestionMapper {


    /**
     * 向Question表中插入数据
     * @param question
     */
    @Insert("INSERT INTO question (title, description, gmt_create, gmt_modified, creator, comment_count, like_count, tag) " +
            "VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{commentCount}, #{tag})")
    void createQuestion(Question question);


    /**
     * 查询所有问题
     * @return
     */
    @Select("SELECT * FROM QUESTION")
    List<Question> selectAll();

}
