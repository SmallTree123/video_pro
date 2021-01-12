package com.nylgsc.video.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.UserFavorMapper;
import com.nylgsc.video.dao.UserMapper;
import com.nylgsc.video.dao.VideosMapper;
import com.nylgsc.video.entity.FavorVO;
import com.nylgsc.video.entity.User;
import com.nylgsc.video.entity.UserFavor;
import com.nylgsc.video.entity.Videos;
import com.nylgsc.video.service.UserFavorService;
import com.nylgsc.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserFavorServiceimpl extends ServiceImpl<UserFavorMapper,UserFavor> implements UserFavorService {

    private final UserFavorMapper userFavorMapper;
    private final UserMapper userMapper;
    private final VideosMapper videosMapper;

    @Transactional
    @Override
    public void handleFavor(FavorVO favorVO) {
        String[] split = favorVO.getUserVideoId().split("-");
        String videoId = split[0];
        String userId = split[1];
        String currentUserId = favorVO.getCurrentUserId();
        User user = new User();
        //如果当前作品就是当前登陆人的,那么需要将获赞数+1
        if (userId.equals(currentUserId)){
            user.setId(currentUserId);
            if ("true".equals(favorVO.getFavor())){
                user.setReceiveLikeCounts(1);
            }else if ("false".equals(favorVO.getFavor())){
                user.setReceiveLikeCounts(-1);
            }
            userMapper.updateInfo(user);
        }
        //如果当前作品是其他人的，需要将我关注的人+1，并且将当前作品作者的粉丝数+1
        if (!userId.equals(currentUserId)){
            //1,如果点的红心，需要将作品作者的获赞认数+1
            user.setId(currentUserId);
            //将我关注人的人 +1
            if (favorVO.getFollow()!=null){
                if ("true".equals(favorVO.getFollow())){
                    user.setFollowCounts(1);
                }else if ("false".equals(favorVO.getFollow())){
                    user.setFollowCounts(-1);
                }
                userMapper.updateInfo(user);
            }

            //将作品作者的获赞+1，并且将粉丝+1
            user.setId(userId);
            if ("true".equals(favorVO.getFavor())){
                user.setReceiveLikeCounts(1);
            }else if ("false".equals(favorVO.getFavor())){
                user.setReceiveLikeCounts(-1);
            }
            if ("true".equals(favorVO.getFollow())){
                user.setFansCounts(1);
            }else if ("false".equals(favorVO.getFollow())){
                user.setFansCounts(-1);
            }
            userMapper.updateInfo(user);

        }

        //先查看用户是否已经点击过，记录存在，已经存在，记录不存在第一次点击
        UserFavor userFavor = this.getOne(new QueryWrapper<UserFavor>().eq("video_id", videoId).eq("user_id", currentUserId));
        if (userFavor == null){
            //第一次点击，保存数据
            UserFavor favor1 = new UserFavor();
            favor1.setUserId(currentUserId);
            favor1.setVideoId(videoId);
            favor1.setFavorStatus(favorVO.getFavor());
            favor1.setFollowStatus(favorVO.getFollow());
            this.save(favor1);

        }else {
            //之后就是修改状态
            userFavor.setFavorStatus(favorVO.getFavor());
            userFavor.setFollowStatus(favorVO.getFollow());
            this.updateById(userFavor);
        }
    }
}
