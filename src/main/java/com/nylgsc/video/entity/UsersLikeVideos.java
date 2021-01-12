package com.nylgsc.video.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UsersLikeVideos implements Serializable {
    private String id;

    private String userId;

    private String videoId;

    private String follow;

    private String delFlag;
}