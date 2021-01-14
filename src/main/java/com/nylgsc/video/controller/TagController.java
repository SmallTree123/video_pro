package com.nylgsc.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nylgsc.video.entity.Tag;
import com.nylgsc.video.service.TagService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "视频标签",tags = {"视频标签"})
public class TagController {

    private final TagService tagService;

    @PostMapping(value = "/queryAllTag")
    public R queryAllTag(@RequestBody Tag tag){
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag",0);

        if (tag.getName()!=null){
            queryWrapper.like("name",tag.getName());
        }
        return R.ok(tagService.list(queryWrapper));
    }
    @DeleteMapping(value = "/delTag")
    public R queryAllTag(Long id){
        tagService.update(new UpdateWrapper<Tag>().set("del_flag",1).eq("id",id));
//        tagService.delTagById(id);
        return R.ok();
    }
}
