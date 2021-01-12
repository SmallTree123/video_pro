package com.nylgsc.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.UsersLikeVideosMapper;
import com.nylgsc.video.entity.UsersLikeVideos;
import com.nylgsc.video.entity.Videos;
import com.nylgsc.video.service.UsersLikeVideosService;
import com.nylgsc.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UsersLikeVideoServiceImpl extends ServiceImpl<UsersLikeVideosMapper, UsersLikeVideos> implements UsersLikeVideosService {

}
