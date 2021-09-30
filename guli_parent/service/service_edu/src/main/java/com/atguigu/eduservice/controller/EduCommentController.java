package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CommentVo;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-26
 */
@RestController
@RequestMapping("/eduservice/educomment")

public class EduCommentController {
    @Autowired
    EduCommentService eduCommentService;

    @Autowired
    UcenterClient ucenterClient;

    @PostMapping("comment")
    public R PublicComment (@RequestBody CommentVo commentVo,@RequestHeader("token") String token) {
        String memberIdToken = JwtUtils.getMemberIdToken(token);
        HashMap memberInfoById = ucenterClient.getMemberInfoById(memberIdToken);


        String nickname = (String) memberInfoById.get("nickname");
        String id = (String) memberInfoById.get("id");
        String avatar = (String) memberInfoById.get("avatar");



        EduComment comment = new EduComment();
        BeanUtils.copyProperties(commentVo, comment);
        comment.setIsDeleted(false);
        comment.setMemberId(id);
        comment.setNickname(nickname);
        comment.setAvatar(avatar);


        eduCommentService.save(comment);
        return R.ok();
    }

    @PostMapping("pageComment/{page}/{limit}/{courseId}")
    public R pageListComment(@PathVariable long page, @PathVariable long limit, @PathVariable String courseId){
        Page<EduComment> CommentPage = new Page<>(page,limit);
        Map<String,Object> map = eduCommentService.getCommentList(CommentPage, courseId);
        return R.ok().data(map);

    }
}

