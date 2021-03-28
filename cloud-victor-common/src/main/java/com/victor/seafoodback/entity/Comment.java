package com.victor.seafoodback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private Integer seafoodId;
    private Integer parent;
    private Integer level;
    private String content;
    private Date date;
    private String fromUserName;
    private String toUserName;
    private List<Comment> children;
}
