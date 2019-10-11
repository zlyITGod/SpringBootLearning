package com.zly.sonrestful.controller;


import com.zly.sonrestful.entity.ProjectInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * SpringBoot 2.0 第一个程序
 */
@RestController
public class HelloController {
    @RequestMapping("/getInfo")
    public ProjectInfo getInfo (){
        ProjectInfo info = new ProjectInfo() ;
        info.setTitle("SpringBoot 2.0 基础教程");
        info.setDate("2019-06-05");
        info.setAuthor("知了一笑");
        return info ;
    }
}
