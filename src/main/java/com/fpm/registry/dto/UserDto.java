package com.fpm.registry.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String position;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
