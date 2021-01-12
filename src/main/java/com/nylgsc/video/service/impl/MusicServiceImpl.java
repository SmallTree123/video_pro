package com.nylgsc.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.video.dao.MusicMapper;
import com.nylgsc.video.entity.Music;
import com.nylgsc.video.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

    @Override
    public List<Music> queryMusicAll(Music music) {
       return baseMapper.selectList(new QueryWrapper<Music>().like("name",music.getName()).or().like("author",music.getName()).eq("del_flag","0"));
    }
}
