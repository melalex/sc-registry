package com.fpm.registry.controllers.view;

import com.fpm.registry.facades.PositionFacade;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/signUp")
public class SignUpViewController {

    private static final String POSITIONS_ATTRIBUTE = "positions";

    private PositionFacade positionFacade;

    @GetMapping
    public String getSignUpView(Model model) {
        model.addAttribute(POSITIONS_ATTRIBUTE, positionFacade.getAll());
        return Views.SIGN_UP;
    }
}
