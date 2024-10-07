package com.example.springpossystem.service.impl;

import jakarta.transaction.Transactional;
import org.example.springpos.dao.CustomerDAO;
import org.example.springpos.dto.CustomerDTO;
import org.example.springpos.entity.Customer;
import org.example.springpos.exception.CustomerNotFoundException;
import org.example.springpos.exception.DataPersistException;
import org.example.springpos.service.CustomerService;
import org.example.springpos.util.AppUtil;
import org.example.springpos.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private Mapping mapping;


    @Override
    public void save(CustomerDTO customerDTO) {
        customerDTO.setId(AppUtil.generateCusId());
        Customer saveCustomer = customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (saveCustomer == null){
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public void delete(String id) {
        if (!customerDAO.existsById(id)){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            customerDAO.deleteById(id);
        }
    }

    @Override
    public void update(String id, CustomerDTO customerDTO) {
        Optional<Customer> customer = customerDAO.findById(id);
        if (customer == null) {
            throw new DataPersistException("Customer not found");
        }else {
            customer.get().setName(customerDTO.getName());
            customer.get().setAddress(customerDTO.getAddress());
            customer.get().setSalary(customerDTO.getSalary());
        }
    }

    @Override
    public CustomerDTO get(String id) {
        if (customerDAO.existsById(id)){
            Customer customer = customerDAO.getReferenceById(id);
            return mapping.toCustomerDto(customer);
        }else {
            throw new DataPersistException("Customer not found");
        }

    }

    @Override
    public List<CustomerDTO> getAll() {
        return mapping.toCustomerList(customerDAO.findAll());
    }
}