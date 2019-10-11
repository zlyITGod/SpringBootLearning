package com.zly.sonmongodb.dao;

import java.util.List;


import com.zly.sonmongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

     Customer findByFirstName(String firstName);
     List<Customer> findByLastName(String lastName);

}