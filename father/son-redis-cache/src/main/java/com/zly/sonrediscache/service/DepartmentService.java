package com.zly.sonrediscache.service;

import com.zly.sonrediscache.entity.Departmet;
import com.zly.sonrediscache.mapper.DepartmentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = {"dept"})
    public Departmet getDept(Integer id) {
        System.out.println("---查询"+id+"号部门---");
        Departmet dept = departmentMapper.getDeptById(id);
        return dept;
    }

    public Departmet getDept2(@Param("id") Integer id) {
        System.out.println("---查询"+id+"号部门---");
        Departmet dept = departmentMapper.getDeptById2(id);
        return dept;
    }


}
