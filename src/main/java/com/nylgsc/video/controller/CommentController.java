package com.nylgsc.video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.video.entity.Comment;
import com.nylgsc.video.service.CommentService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
@Api(value = "评论模块",tags = {"评论模块controller"})
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/getCommentByVideoId")
    public R getCommentByVideoId(@RequestParam("videoId")String videoId){
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().eq("video_id",videoId).eq("del_flag","0").orderByDesc("create_time"));

        return R.ok(commentList);
    }
    @PostMapping(value = "/saveComment")
    public R saveComment(@RequestBody Comment comment){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        comment.setCreateTime(format);
        commentService.save(comment);
        return R.ok();
    }

}
