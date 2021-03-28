package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.CommentService;
import com.victor.seafoodback.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/getComment")
    public CommonResult getAllCommentBySeafood(@RequestParam("seafoodId") Integer seafoodId) {
        return commentService.getAllCommentBySeafood(seafoodId);
    }

    @PostMapping("/addComment")
    public CommonResult addComment(@RequestParam("fromUserId") Integer fromUserId, @RequestParam(value = "toUerId",required = false) Integer toUserId,
                                   @RequestParam("seafoodId") Integer seafoodId,
                                   @RequestParam(value = "parent",required = false) Integer parent,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("content") String content,
                                   @RequestParam("fromUserName") String fromUserName,@RequestParam(value = "toUserName",required = false) String toUserName) {
        Comment comment = new Comment();
        comment.setFromUserId(fromUserId);
        comment.setFromUserName(fromUserName);
        comment.setToUserId(toUserId);
        comment.setToUserName(toUserName);
        comment.setParent(parent);
        comment.setSeafoodId(seafoodId);
        comment.setLevel(level);
        comment.setContent(content);
        comment.setDate(new Date());
        return commentService.addComment(comment);
    }
}
