package com.fpm.registry.forms;

import com.fpm.registry.constraints.FieldMatch;
import com.fpm.registry.constraints.HardPassword;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "repeatPassword")
public class SignUpForm {

    @Size(min = 3, max = 40)
    private String login;

    @HardPassword
    private String password;
    private String repeatPassword;

    @Size(min = 3, max = 40)
    private String firstName;

    @Size(min = 3, max = 40)
    private String lastName;

    @NotNull
    private String position;
}
