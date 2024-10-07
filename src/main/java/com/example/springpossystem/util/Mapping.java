package com.example.springpossystem.util;

import org.example.springpos.dto.CustomerDTO;
import org.example.springpos.dto.ItemDTO;
import org.example.springpos.entity.Customer;
import org.example.springpos.entity.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO,Customer.class);
    }

    public CustomerDTO toCustomerDto(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }

    public List<CustomerDTO> toCustomerList(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer:customerList) {
            customerDTOList.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return customerDTOList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public Item toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, Item.class);
    }

    public ItemDTO toItemDto(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> toItemList(List<Item> itemList){
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item:itemList) {
            itemDTOList.add(new ItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQty()
            ));
        }
        return itemDTOList;
    }

}
