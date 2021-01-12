package com.nylgsc.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.video.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updateInfo(User user);
}