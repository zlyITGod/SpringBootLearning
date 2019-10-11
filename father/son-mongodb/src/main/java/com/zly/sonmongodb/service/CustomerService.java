package com.zly.sonmongodb.service;

import com.zly.sonmongodb.dao.CustomerRepository;
import com.zly.sonmongodb.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomer(String id) {
        return repository.findById(id);
    }

    /**
     *  新增和修改都是save方法
     *  id存在为修改,id不存在为新增
     */
    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return repository.save(customer);
    }

    public void deleteCustomer(String id) {
        repository.findById(id)
                .ifPresent(customer -> repository.delete(customer));
    }

    public void updateCustomer(String id, Customer customer) {
        repository.findById(id)
                .ifPresent(
                        customer1 -> {
                            customer1.setFirstName(customer.getFirstName());
                            customer1.setLastName(customer.getLastName());
                            repository.save(customer1);
                        }
                );
    }



}
