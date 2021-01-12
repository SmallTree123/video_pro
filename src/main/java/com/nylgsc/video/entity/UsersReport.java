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
public class UsersReport implements Serializable {
    private String id;

    private String dealUserId;

    private String dealVideoId;

    private String title;

    private String content;

    private String userid;

    private Date createDate;

}