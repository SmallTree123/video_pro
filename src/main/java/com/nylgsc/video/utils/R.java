package com.nylgsc.video.utils;


import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    //响应码
    private Integer status;

    //返回的信息
    private String msg;

    //响应体
    private Object data;


    public static R ok(){
        R r = new R();
        r.status = ReturnCode.SUCCESS;
        return r;
    }

    public static R ok(Object data){
        R r = new R();
        r.status = ReturnCode.SUCCESS;
        r.data=data;
        return r;
    }

    public static R ok(String msg,Object data){
        R r = new R();
        r.status = ReturnCode.SUCCESS;
        r.msg = msg;
        r.data = data;
        return r;
    }

    public static R faild(String msg){
        R r = new R();
        r.status = ReturnCode.ERROR;
        r.msg = msg;
        return r;
    }

    public static R faild(String msg,Object data){
        R r = new R();
        r.status = ReturnCode.ERROR;
        r.msg = msg;
        r.data = data;
        return r;
    }
}
