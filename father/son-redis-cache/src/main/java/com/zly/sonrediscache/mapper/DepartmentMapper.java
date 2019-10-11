package com.zly.sonrediscache.mapper;

import com.zly.sonrediscache.entity.Departmet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface DepartmentMapper {


    @Select("select * from department where id = #{id}")
    public Departmet getDeptById(Integer id);

    @Select("select * from department where id = #{id}")
    public Departmet getDeptById2(@Param("id") Integer id);


}
