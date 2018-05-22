package com.fpm.registry.controllers.api;

import com.fpm.registry.dto.UserDto;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.utils.Urls;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {

    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody SignUpForm form) {
        return ResponseEntity.created(URI.create(Urls.LOGIN))
                .body(userFacade.create(form));
    }
}
