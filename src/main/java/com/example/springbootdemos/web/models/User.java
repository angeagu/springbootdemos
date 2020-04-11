package com.example.springbootdemos.web.models;

import lombok.Data;

@Data
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
}
