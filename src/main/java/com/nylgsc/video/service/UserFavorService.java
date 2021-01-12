package com.nylgsc.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.video.entity.FavorVO;
import com.nylgsc.video.entity.UserFavor;


public interface UserFavorService extends IService<UserFavor> {
    void handleFavor(FavorVO favorVO);
}
