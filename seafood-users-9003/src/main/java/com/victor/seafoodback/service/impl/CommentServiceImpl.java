package com.victor.seafoodback.service.impl;

import com.victor.seafoodback.dao.CommentDao;
import com.victor.seafoodback.entity.Comment;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public CommonResult getAllCommentBySeafood(Integer seafoodId) {
        List<Comment> allCommentBySeafood = commentDao.getAllCommentBySeafood(seafoodId);
        return new CommonResult(200, "获取评论成功", handleCommentList(allCommentBySeafood));
    }

    @Override
    public CommonResult addComment(Comment comment) {
        commentDao.addComment(comment);
        return new CommonResult(200,"发表评论成功");
    }

    /**
     * 处理评论列表
     *
     * @param comments
     * @return
     */
    public List<Comment> handleCommentList(List<Comment> comments) {
        List<Comment> commentList = new ArrayList<>();

        for (Comment comment : comments) {
            if (comment.getLevel() == 1) {
                List<Comment> children = new ArrayList();
                for (Comment comment1 : comments) {
                    if (comment1.getLevel() == 2 && comment1.getParent() == comment.getId()) {
                        children.add(comment1);
                    }
                }
                comment.setChildren(children);
                commentList.add(comment);
            }
        }
        return commentList;
    }
}
