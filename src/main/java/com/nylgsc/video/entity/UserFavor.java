package com.nylgsc.video.entity;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class UserFavor {
    private String id;

    private String userId;

    private String videoId;

    private String followStatus;

    private String favorStatus;
}