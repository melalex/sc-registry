package com.fpm.registry.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String position;
}
