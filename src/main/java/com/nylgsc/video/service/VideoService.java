package com.nylgsc.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.video.entity.Videos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService extends IService<Videos> {

    //上传视频
    void uploadVideo(String userId,String videoDecr,String selectedTags,String userProfile, MultipartFile file);

    List<Videos> queryVideo(Videos videos);

    List<Videos> queryVideoByUserLike(String userId);
}
