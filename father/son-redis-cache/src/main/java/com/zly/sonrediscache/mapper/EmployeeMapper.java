package com.zly.sonrediscache.mapper;

import com.zly.sonrediscache.entity.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} where id=#{id} ")
    public void updateEmp(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void deleteEmp(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id}values (#{lastName},#{email},#{gender},#{dId}  ))")
    public void insertEmploree(Employee employee);

    @Select("select * from emplyee where lastName = #{lastName}")
    public Employee getEmpByLastName(String lastName);

}
