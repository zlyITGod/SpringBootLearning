package com.zly.sonrestful.controller;


import com.zly.sonrestful.config.ParamConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 环境配置，参数绑定
 */
@RestController
public class ParamController {

    @Resource
    private ParamConfig paramConfig ;

    @RequestMapping("/getParam")
    public String getParam (){
        return "["+paramConfig.getAuthor()+";"+
                paramConfig.getTitle()+";"+
                paramConfig.getTime()+"]" ;
    }
}
