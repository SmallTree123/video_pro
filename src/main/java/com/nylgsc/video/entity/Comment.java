package com.nylgsc.video.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private String id;

    private String videoId;

    private String fromUserId;

    private String fromUserProfile;

    private String parentId;

    private String parentProfile;

    private String comment;

    private String createTime;

    private String delFlag;

}