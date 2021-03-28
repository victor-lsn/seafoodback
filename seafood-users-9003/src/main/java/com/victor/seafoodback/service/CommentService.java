package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;

public interface CommentService {
    CommonResult getAllCommentBySeafood(Integer seafoodId);

    CommonResult addComment(Comment comment);
}
