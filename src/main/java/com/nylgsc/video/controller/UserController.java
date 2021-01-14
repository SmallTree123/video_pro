package com.nylgsc.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nylgsc.video.entity.User;
import com.nylgsc.video.service.UserService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Api(value = "用户信息",tags = {"用户信息"})
public class UserController {

    private final UserService userService;

    @PostMapping("/getAllUser")
    public R getAllUser(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag",0);
        if (user.getUsername()!=null){
            queryWrapper.like("username",user.getUsername());
        }
        if (user.getNickname()!=null){
            queryWrapper.like("nickname",user.getNickname());
        }
        return R.ok(userService.list(queryWrapper));
    }

    @DeleteMapping("/delUser")
    public R delUser(Long userId){
        userService.list(new UpdateWrapper<User>().set("del_flag",1).eq("id",userId));
        return R.ok();
    }
}
