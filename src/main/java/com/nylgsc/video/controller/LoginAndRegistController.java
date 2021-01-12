package com.nylgsc.video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.video.config.RedisConfig;
import com.nylgsc.video.entity.User;
import com.nylgsc.video.entity.Videos;
import com.nylgsc.video.service.UserService;
import com.nylgsc.video.service.VideoService;
import com.nylgsc.video.utils.MD5Utils;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
@Api(value = "用户登录和注册接口",tags = {"用户登录注册的controller"})
public class LoginAndRegistController {

    private final UserService userService;
    private final VideoService videoService;
    private final RedisTemplate redisTemplate;

    @ApiOperation(value = "新用户注册",notes = "新用户注册")
    @PostMapping(value = "/regist")
    public R registerUser(@RequestBody User user){
        //先检验姓名和密码是否为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return R.faild("请先输入用户名或者密码");
        }
        //检验用户名是否存在
        User name = userService.validateUserName(user.getUsername());
        if (name == null){
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userService.saveUser(user);
        }else {
            return R.faild("用户名已经存在");
        }
        return R.ok(user);
    }

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping(value = "/login")
    public R loginUser(@RequestBody User user){

//        if (redisTemplate.opsForValue().get(RedisConfig.USER_REDIS_SESSION+":"+user.getId())==null){
//            //设置token
//            String uniqueToken = UUID.randomUUID().toString().replace("-", "");
//            redisTemplate.opsForValue().set(RedisConfig.USER_REDIS_SESSION+":"+user.getId(),uniqueToken,1000*60*30);
//            user.setUserToken(uniqueToken);
//            return R.faild("用户登录已过期，请重新登录");
//        }

        //先检验姓名和密码是否为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return R.faild("请先输入用户名或者密码");
        }
        //查询该用户有没有注册
        User name = userService.validateUserName(user.getUsername());
        if (name == null){
            return R.faild("请先注册");
        }
        //检验用户名和密码是否正确
        User dbUser = userService.validateIsRegist(user);
        if (dbUser == null){
            return R.faild("用户名或密码错误");
        }
        Map map = new HashMap<>();
        List<Videos> list = videoService.list(new QueryWrapper<Videos>().eq("user_id", dbUser.getId()).eq("del_flag", "0"));
        map.put("user",dbUser);
        map.put("userVideo",list);
        return R.ok(dbUser);
    }

    @ApiOperation(value = "用户注销",notes = "用户注销")
    @DeleteMapping(value = "/exit/{userId}")
    public R exitUser(String userId){
//        redisTemplate.delete(RedisConfig.USER_REDIS_SESSION+":"+userId);
        return R.ok();
    }

    @ApiOperation(value = "返回邮箱验证码",notes = "返回邮箱验证码")
    @GetMapping(value = "/getEmailCode/{email}")
    public R getEmailCode(@PathVariable("email") String mail) throws EmailException {

        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(mail);
        if (!m.matches()){
            return R.faild("邮箱格式不正确");
        }
        //先生成一个六位数验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.qq.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        email.addTo(mail);//设置收件人
        email.setFrom("1793401593@qq.com","南瓜短视频");//发送人的邮箱为自己的，用户名可以随便填
        email.setAuthentication("1793401593@qq.com","gxsuiozqujqhbbaj");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject("注册验证码");//设置发送主题
        email.setMsg(code);//设置发送内容
        email.send();//进行发送
        return R.ok(code);
    }

}
