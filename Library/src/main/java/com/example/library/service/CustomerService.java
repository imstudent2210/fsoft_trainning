package com.example.library.service;

import com.example.library.dto.CustomerDTO;
import com.example.library.model.Customer;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDto);

    Customer findByUsername(String username);

    Customer saveInfor(Customer customer);
}
