package com.fpm.registry.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignInDto {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
