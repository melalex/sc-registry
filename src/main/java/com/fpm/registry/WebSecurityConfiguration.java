package com.fpm.registry;

import com.fpm.registry.services.ConfigurationService;
import com.fpm.registry.utils.Urls;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Setter(onMethod = @__(@Autowired))
    private ConfigurationService configurationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        @formatter:off
        http.authorizeRequests()
                .antMatchers(Urls.REGISTER, Urls.WEBJARS, Urls.CSS, Urls.JS, Urls.CREATE_USER, Urls.LOGIN).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(Urls.LOGIN)
                .defaultSuccessUrl(Urls.INDEX)
                .failureForwardUrl(Urls.LOGIN_FAIL)
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl(Urls.INDEX);
//        @formatter:on
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsServiceAdapter) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceAdapter);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(configurationService.getEncoderStrength());
    }
}
