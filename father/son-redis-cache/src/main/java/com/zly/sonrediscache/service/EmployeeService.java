package com.zly.sonrediscache.service;


import com.zly.sonrediscache.entity.Employee;
import com.zly.sonrediscache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emps") //抽取缓存的公共配置  虽然没用到哈哈
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中取，不用调用方法
     * CacheManager管理多个Cache组件,每一个缓存组件有自己唯一的名字
     * 几个属性
     * CacheNames/value:指定缓存组件的名字
     * key:缓存数据使用的key,可以用它来指定. #id 参数id的值
     * KeyGenerator:key的生成器,可以自己指定key的生成器的组件id key/keygennerator二选一
     * cacheManager:指定缓存管理器;或者cacheResolver指定获取解析器 也是二选一
     * condition:指定符合条件的情况才缓存, condition = "#id>0"
     * unless:否定缓存;当unless指定的条件为true,方法的返回值就不会被缓存 unless="#result==null"
    */

    @Cacheable(cacheNames = {"emps"})
    public Employee getEmp(Integer id) {
        System.out.println("---查询"+id+"号员工---");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * cacheput : 既调用方法,又更新缓存数据
     * 修改了数据库的某个数据,同时更新缓存;
     * 运行时机:
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     *
     * 测试步骤:
     * 1 查询1号员工:查询到的结果放在缓存中 key:1   value:lastName:张三
     * 2 以后查询还是之前的结果
     * 3 更新1号员工:[lastName:zhangsan;gender:0] 将方法的返回值也放进缓存
     *              key:传入的employee对象  值:返回的employee对象
     * 4 查询一号员工
     *          应该是更新后的员工；
     *  *                  key = "#employee.id":使用传入的参数的员工id；
     *  *                  key = "#result.id":使用返回的id
     *  *                      @Cacheable的key是不能用#result
     *  *          为什么是没更新之前的？【1号员工没有再缓存中更新】
     *
     */
    @CachePut(value = "emp" ,key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }
/**
 * @CacheEvict : 缓存清除
 * key : 指定要清除的数据
 * allEntries = true : 指定要清除所有的数据
 * beforeInvocation = false : 缓存的清除是否再方法之前执行
 *          默认代表缓存清除操作是在方法执行后执行 ； 如果出现异常缓存就不会清除
 * beforeInvocation = true : 代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
 *
 * @param id
 */
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp : "+id);
    //        employeeMapper.deleteEmp(id);
}
    /**
     * @Cacheing : 定义复杂的缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value="emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email"),
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
