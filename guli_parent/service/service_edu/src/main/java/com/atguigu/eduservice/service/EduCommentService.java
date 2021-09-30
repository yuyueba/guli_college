package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-26
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentList(Page<EduComment> commentPage, String courseId);
}
