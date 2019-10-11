package com.zly.sonmongodb;

import com.zly.sonmongodb.dao.CustomerRepository;
import com.zly.sonmongodb.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SonMongodbApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SonMongodbApplication.class, args);
    }

}
