package com.fpm.registry.forms;

import com.fpm.registry.constraints.AtLeastOneDigit;
import com.fpm.registry.constraints.AtLeastOneLowerCaseLatter;
import com.fpm.registry.constraints.AtLeastOneSpecialCharacter;
import com.fpm.registry.constraints.AtLeastOneUpperCaseLatter;
import com.fpm.registry.constraints.FieldMatch;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "repeatPassword")
public class SignUpForm {

    @Size(min = 3, max = 40)
    private String login;

    @NotNull
    @AtLeastOneDigit
    @Size(min = 6, max = 255)
    @AtLeastOneLowerCaseLatter
    @AtLeastOneUpperCaseLatter
    @AtLeastOneSpecialCharacter
    private String password;
    private String repeatPassword;

    @Size(min = 3, max = 40)
    private String firstName;

    @Size(min = 3, max = 40)
    private String lastName;

    @NotNull
    private String position;
}
