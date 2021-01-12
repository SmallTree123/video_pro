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
public class Music implements Serializable {
    private String id;

    private String author;

    private String name;

    private String path;

    private String delFlag;

}