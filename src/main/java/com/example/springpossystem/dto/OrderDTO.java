package com.example.springpossystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    String orderId;
    Date orderDate;
    String cusId;
    List<ItemDTO> items;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;
}
