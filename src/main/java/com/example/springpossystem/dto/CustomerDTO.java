package com.example.springpossystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements SuperDTO {
    String id;
    String name;
    String address;
    double salary;
}
