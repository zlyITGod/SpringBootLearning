package com.zly.sonmongodb.web;


import com.zly.sonmongodb.entity.Customer;
import com.zly.sonmongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cus")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer createCustomer(Customer customer) {
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") String id, Customer customer) {
        customerService.updateCustomer(id, customer);
    }

    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 null
     */
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id).orElse(null);
    }

}
