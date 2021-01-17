package com.nylgsc.video.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Videos implements Serializable {
    private String id;

    private String userId;

    private String tagId;

    private String videoDesc;

    private String videoPath;

    private Integer videoSeconds;

    private String videoUserProfile;

    private Integer videoHeight;

    private String coverPath;

    private Long likeCounts;

    private Integer status;

    private Long createTime;

    private String delFlag;

    private String tagName;

}