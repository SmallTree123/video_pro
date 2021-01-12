package com.nylgsc.video.controller;


import com.nylgsc.video.service.UserService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Component
@RestController
@RequestMapping("/userInfo")
@AllArgsConstructor
@Api(value = "用户上传头像以及视频的接口",tags = {"用户基础业务controller"})
public class UserInfoController {

   private final UserService userService;

   @RequestMapping("/uploadProfile")
   public R uploadProfile(String userId, @RequestParam("file")MultipartFile file){
       return userService.uploadProfile(userId, file);

   }
}
