package org.example.springpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements SuperDTO{
    String id;
    String name;
    double price;
    int qty;
}
