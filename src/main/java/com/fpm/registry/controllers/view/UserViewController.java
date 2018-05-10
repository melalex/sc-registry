package com.fpm.registry.controllers.view;

import com.fpm.registry.dto.UserDto;
import com.fpm.registry.facades.PositionFacade;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class UserViewController {

    private static final String POSITIONS_ATTRIBUTE = "positions";

    private UserFacade userFacade;
    private PositionFacade positionFacade;

    @PostMapping("/users")
    public ModelAndView create(@Valid @RequestBody SignUpForm dto, HttpServletRequest request) {
        UserDto userDto = userFacade.create(dto);
        WebAuthenticationDetails details = new WebAuthenticationDetails(request);

        userFacade.login(userDto, details);

        return Views.redirectToIndex();
    }

    @GetMapping("/signUp")
    public String getSignUpView(Model model) {
        model.addAttribute(POSITIONS_ATTRIBUTE, positionFacade.getAll());
        return Views.SIGN_UP;
    }
}
