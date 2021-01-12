package com.nylgsc.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.video.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}