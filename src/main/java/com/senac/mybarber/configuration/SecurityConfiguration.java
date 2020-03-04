package com.senac.mybarber.configuration;

import com.senac.mybarber.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers(HttpMethod.POST, "/authenticate", "/clientes", "/profissionais").permitAll()
                .anyRequest().authenticated()
                .and()

                // filtra requisições de login
                .addFilterBefore(new JWTLoginFilter("/authenticate", authenticationManager(), tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)

                // filtra outras requisições para verificar a presença do JWT no header
                .addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    // provisorio, ate que o JWT esteja funcional
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }
}
