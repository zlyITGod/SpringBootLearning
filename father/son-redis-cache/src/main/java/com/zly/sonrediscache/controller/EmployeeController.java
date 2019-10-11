package com.zly.sonrediscache.controller;


import com.zly.sonrediscache.entity.Employee;
import com.zly.sonrediscache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmp(id);
        return emp;
    }
    @GetMapping("/emp")
    public Employee update(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }
    @GetMapping("deleteemp")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable(value = "lastName") String lastName)  {
        return employeeService.getEmpByLastName(lastName);
    }

}
