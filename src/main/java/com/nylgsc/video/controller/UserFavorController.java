package com.nylgsc.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.video.entity.FavorVO;
import com.nylgsc.video.entity.UserFavor;
import com.nylgsc.video.service.UserFavorService;
import com.nylgsc.video.service.UsersLikeVideosService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Component
@RestController
@RequestMapping("/favor")
@AllArgsConstructor
@Api(value = "用户点赞喜欢的视频",tags = {"用户点赞喜欢的视频"})
public class UserFavorController {

    private final UserFavorService userFavorService;

    @PostMapping(value = "/handleFavor")
    public R handleFavor(@RequestBody FavorVO favorVO){
        userFavorService.handleFavor(favorVO);
        return R.ok();
    }

    @GetMapping(value = "/getAllUserFavor")
    public R getAllUserFavor(String userId){
        List<UserFavor> list = userFavorService.list(new QueryWrapper<UserFavor>().eq("userId", userId));
        return R.ok(list);
    }

}
