package com.nylgsc.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.TagMapper;
import com.nylgsc.video.entity.Tag;
import com.nylgsc.video.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
