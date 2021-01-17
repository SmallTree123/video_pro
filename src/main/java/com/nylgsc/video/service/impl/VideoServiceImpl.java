package com.nylgsc.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.UsersLikeVideosMapper;
import com.nylgsc.video.dao.VideosMapper;
import com.nylgsc.video.entity.Music;
import com.nylgsc.video.entity.UsersLikeVideos;
import com.nylgsc.video.entity.Videos;
import com.nylgsc.video.service.MusicService;
import com.nylgsc.video.service.UsersLikeVideosService;
import com.nylgsc.video.service.VideoService;
import com.nylgsc.video.utils.MergeVideoMusic;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideosMapper, Videos> implements VideoService {

    private static final String fileSpace = "D:/video";

    private final UsersLikeVideosMapper usersLikeVideosMapper;

    @Override
    public void uploadVideo(String userId,String videoDecr,String selectedTags,String userProfile, MultipartFile file){
        String originalFilename = file.getOriginalFilename().replace(".","");
        String f = originalFilename.substring(0,originalFilename.length()-3)+".mp4";



        Videos videos = new Videos();
        videos.setVideoUserProfile(userProfile);
        videos.setUserId(userId);
        videos.setVideoDesc(videoDecr);
        videos.setTagName(selectedTags);
        videos.setCreateTime(System.currentTimeMillis());
        videos.setStatus(1);

        String savePathDB = "/"+userId+"/video";
        String finalFilePath = "";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            String fileName = f;
            if (StringUtils.isNotBlank(fileName)){
                //文件上传的最终保存路径
                finalFilePath = fileSpace+savePathDB+"/"+fileName;
                //设置数据库保存路径
                savePathDB += ("/"+fileName);

                File outFile = new File(finalFilePath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()){
                    //创建父文件夹
                    outFile.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream,fileOutputStream);

                videos.setCoverPath(finalFilePath);
                videos.setVideoPath(savePathDB);
                this.save(videos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null && inputStream != null) {
                try {
                    fileOutputStream.flush();
                    inputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public List<Videos> queryVideo(Videos videos) {
        QueryWrapper<Videos> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag","0");
        if (StringUtils.isNotEmpty(videos.getUserId())){
            queryWrapper.eq("user_id",videos.getUserId());
        }
        if (StringUtils.isNotEmpty(videos.getVideoDesc())){
            queryWrapper.like("video_desc",videos.getVideoDesc());
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Videos> queryVideoByUserLike(String userId) {
        List<String> collect = usersLikeVideosMapper.selectList(new QueryWrapper<UsersLikeVideos>().eq("user_id", userId).eq("del_flag", "0")).stream().map(UsersLikeVideos::getVideoId).collect(Collectors.toList());
        List<Videos> videosList = this.listByIds(collect);
        return videosList;

    }
}
