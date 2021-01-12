package com.nylgsc.video.controller;

import com.nylgsc.video.service.TagService;
import com.nylgsc.video.utils.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@Api(value = "视频标签",tags = {"视频标签"})
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "/queryAllTag")
    public R queryAllTag(){
        return R.ok(tagService.list());
    }
}
