package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    List<Comment> getAllCommentBySeafood(Integer seafoodId);

    Integer addComment(Comment comment);
}
