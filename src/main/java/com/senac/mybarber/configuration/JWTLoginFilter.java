package com.senac.mybarber.configuration;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    private TokenAuthenticationService tokenAuthenticationService;

    protected JWTLoginFilter(String url,
                             AuthenticationManager authManager,
                             TokenAuthenticationService tokenAuthenticationService) {
        super(new AntPathRequestMatcher(url));
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        AccountCredentials credentials = new ObjectMapper()
                .readValue(request.getInputStream(), AccountCredentials.class);

        //todo: Ao realizar validação com usuários presentes no banco de dados utilizaar esse ponto do código

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        tokenAuthenticationService.addAuthentication(response, auth.getName());
    }
}
