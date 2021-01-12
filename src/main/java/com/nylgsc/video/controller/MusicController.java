package com.nylgsc.video.controller;


import com.nylgsc.video.entity.Music;
import com.nylgsc.video.service.MusicService;
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
@RequestMapping("/music")
@AllArgsConstructor
@Api(value = "bgm接口层",tags = {"bgm基础业务controller"})
public class MusicController {

    private final MusicService musicService;

    @RequestMapping(value = "/query")
    public R queryMusicAll(@RequestBody Music music){
        return R.ok(musicService.queryMusicAll(music));
    }

}
