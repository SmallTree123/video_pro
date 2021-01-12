package com.nylgsc.video.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Tag {
    private Integer id;

    private String name;

    private String delFlag;
}