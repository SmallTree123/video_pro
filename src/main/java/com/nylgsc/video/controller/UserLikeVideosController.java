package com.nylgsc.video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.video.entity.UsersLikeVideos;
import com.nylgsc.video.service.UsersLikeVideosService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
@RequestMapping("/follow")
@AllArgsConstructor
@Api(value = "用户关注的视频",tags = {"用户关注的视频"})
public class UserLikeVideosController {

    private final UsersLikeVideosService usersLikeVideosService;

    @RequestMapping(value = "/handleFollow")
    public R handleAttention(){
        return R.ok();
    }

    @RequestMapping(value = "/getAllFollow")
    public R getAllAttention(String userId){
        List<UsersLikeVideos> list = usersLikeVideosService.list(new QueryWrapper<UsersLikeVideos>().eq("user_id", userId));
        return R.ok(list);
    }
}
