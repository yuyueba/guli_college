package com.atguigu.eduservice.entity.vo;

import lombok.Data;

@Data
public class CommentVo {
    private String content;
    private String courseId;
    private String teacherId;
    private String nickname;
}
