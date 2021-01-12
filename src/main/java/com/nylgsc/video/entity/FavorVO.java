package com.nylgsc.video.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FavorVO {
    private String userVideoId;
    private String currentUserId;
    private String favor;
    private String follow;
}
