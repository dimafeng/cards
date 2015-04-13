package com.dimafeng.cards.config;

import com.dimafeng.cards.service.UserPrincipal;
import com.dimafeng.cards.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService).passwordEncoder(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                /**
                 * TODO enable csrf
                 */
                .csrf()
                .disable()
                .formLogin()
                        /**
                         * We don't need to redirect after login success (because
                         * it breaks angular rest client). We need to return current user
                         */
                .successHandler(
                        (request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            PrintWriter writer = response.getWriter();
                            new ObjectMapper().writeValue(writer,
                                    ((UserPrincipal) authentication.getPrincipal()).getUser());
                            writer.flush();
                        })
                .and()
                .logout()
                        /**
                         * We don't need to redirect after logout. We just need
                         * to send 200 OK code
                         */
                .logoutSuccessHandler(
                        (request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_OK)
                )
                .and()
                .httpBasic()
                .and()
                        /**
                         * If user isn't logged in and he's trying to access resource which
                         * marked by @Secured annotation, we don't need to redirect request to
                         * /login page, we just need to return 403 error code
                         */
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

}
