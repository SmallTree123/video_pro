package com.nylgsc.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.video.entity.User;
import com.nylgsc.video.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends IService<User> {

    //校验用户名是否存在
    User validateUserName(String username);

    //保存注册的对象
    void saveUser(User user);

    User validateIsRegist(User user);

    R uploadProfile(String userId, MultipartFile file);
}
