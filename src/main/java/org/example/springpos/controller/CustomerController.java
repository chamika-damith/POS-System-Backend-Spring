package org.example.springpos.controller;

import org.example.springpos.dto.CustomerDTO;
import org.example.springpos.exception.CustomerNotFoundException;
import org.example.springpos.exception.DataPersistException;
import org.example.springpos.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    private Logger logger= LoggerFactory.getLogger(CustomerController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){
        logger.info("Inside the customer save method");
        try {
            customerService.save(customerDTO);
            logger.info("customer save successful");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            logger.error("Error saving customer");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId,@RequestBody CustomerDTO customerDTO){
        logger.info("Inside the customer update method");
        try {
            customerService.update(customerId,customerDTO);
            logger.info("customer update successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            logger.error("Error update customer");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{customerId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") String customerId){
        logger.info("Inside the customer get method");
        try {
            CustomerDTO customerDTO = customerService.get(customerId);
            return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAll();
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        logger.info("Inside the customer delete method");
        try {
            customerService.delete(customerId);
            logger.info("customer delete successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            logger.error("Error delete customer");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
