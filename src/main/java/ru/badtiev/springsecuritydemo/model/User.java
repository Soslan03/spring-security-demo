package ru.badtiev.springsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String second_name;
    private int age;
    private String email;


}
