package com.zly.sonrediscache.controller;

import com.zly.sonrediscache.entity.Departmet;
import com.zly.sonrediscache.service.DepartmentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    /**
     * 没有查数据库 有的话直接返回
     */
    @GetMapping("/dept/{id}")
    public Departmet getDepartment(@PathVariable("id") Integer id) {
        return departmentService.getDept(id);
    }

    /**
     * 查一次就去一次数据库
     */
    @GetMapping("/dept")
    public Departmet getDepartment2(@Param("id") Integer id) {
        return departmentService.getDept2(id);
    }
}
