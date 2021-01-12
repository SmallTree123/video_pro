package com.nylgsc.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.UserMapper;
import com.nylgsc.video.entity.User;
import com.nylgsc.video.service.UserService;
import com.nylgsc.video.utils.MD5Utils;
import com.nylgsc.video.utils.R;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private static final String fileSpace = "D:/video";

    @Override
    public User validateUserName(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username",username).eq("del_flag","0"));
    }

    @Override
    public void saveUser(User user) {
        baseMapper.insert(user);
    }

    @Override
    public User validateIsRegist(User user) {
        String password = MD5Utils.getMD5Str(user.getPassword());
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()).eq("password", password).eq("del_flag","0"));
    }

    @Override
    public R uploadProfile(String userId, MultipartFile file) {
        User user = baseMapper.selectById(userId);
        //保存到数据库中的相对路径
        String savePathDB = "/"+userId+"/profile";
        if (file == null){
            return R.faild("数据");
        }
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalFilePath = "";
        try {
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)){
                //文件上传的最终保存路径
                finalFilePath = fileSpace+savePathDB+fileName;
                //设置数据库保存路径
                savePathDB += ("/"+fileName);
                user.setFaceImage(savePathDB);

                File outFile = new File(finalFilePath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()){
                    //创建父文件夹
                    outFile.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream,fileOutputStream);

                //修改数据库中保存的图片地址
                this.updateById(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null && inputStream != null) {
                try {
                    fileOutputStream.flush();
                    inputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return R.ok(savePathDB);
    }
}
