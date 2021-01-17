package com.nylgsc.video.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.video.entity.Tag;
import com.nylgsc.video.entity.UserFavor;
import com.nylgsc.video.entity.Videos;
import com.nylgsc.video.service.TagService;
import com.nylgsc.video.service.UserFavorService;
import com.nylgsc.video.service.UsersLikeVideosService;
import com.nylgsc.video.service.VideoService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RestController
@RequestMapping("/video")
@AllArgsConstructor
@Api(value = "短视频",tags = {"短视频基础业务controller"})
public class VideoController {

    private final VideoService videoService;
    private final UserFavorService userFavorService;
    private final TagService tagService;

    /**
     * 用户上传视频
     * @param userId
     * @param videoDecr
     * @param userProfile
     * @param file
     * @return
     */
    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public R uploadVideo(String userId, String videoDecr,String selectedTags, String userProfile, MultipartFile file){
        videoService.uploadVideo(userId,videoDecr,selectedTags,userProfile,file);
        return R.ok();
    }


    /**
     * 查询所有视频
     *
     * @return
     */
    @PostMapping(value = "/query")
    public R queryVideo(@RequestBody Videos videos){
        List<Videos> returnVideos = videoService.queryVideo(videos);
        List<UserFavor> list = userFavorService.list();
        Map map = new HashMap<>();
        map.put("videos",returnVideos);
        map.put("userFavors",list);
        return R.ok(map);
    }

    /**
     * 查询所有视频
     *
     * @return
     */
    @PostMapping(value = "/queryForBase")
    public R queryVideoForBase(@RequestBody Videos videos){
        QueryWrapper<Videos> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(videos.getTagName())){
            queryWrapper.like("tag_name",videos.getTagName());
        }
        if (StringUtils.isNotEmpty(videos.getVideoDesc())){
            queryWrapper.like("video_desc",videos.getVideoDesc());
        }
        return R.ok(videoService.list(queryWrapper));
    }


    /**
     * 返回用户关注的视频信息
     * @return
     */
    @GetMapping(value = "/queryUserLike")
    public R queryVideoByUserLike(@RequestParam(value = "userId",required = false)String userId){
        return R.ok(videoService.queryVideoByUserLike(userId));
    }

    @GetMapping(value = "/queryVideoByUserId")
    public R queryVideoByUserId(@RequestParam(value = "userId",required = false)String userId){
        return R.ok(videoService.list(new QueryWrapper<Videos>().eq("user_id",userId).eq("del_flag","0")));
    }

    @PutMapping("/updateVideo")
    public R updateVideo(@RequestBody Videos videos){
        videoService.updateById(videos);
        return R.ok();
    }

}
