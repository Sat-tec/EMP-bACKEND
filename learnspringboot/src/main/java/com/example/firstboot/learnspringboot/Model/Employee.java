package com.example.firstboot.learnspringboot.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
}
