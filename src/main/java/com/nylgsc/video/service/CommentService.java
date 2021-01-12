package com.nylgsc.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.video.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentService extends IService<Comment> {
}
