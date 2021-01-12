package com.nylgsc.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.video.entity.Music;

import java.util.List;

public interface MusicService extends IService<Music> {

    List<Music> queryMusicAll(Music music);
}
