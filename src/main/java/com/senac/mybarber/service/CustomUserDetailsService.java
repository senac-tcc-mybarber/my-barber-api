package com.senac.mybarber.service;

import com.senac.mybarber.model.UserPrincipal;
import com.senac.mybarber.model.Pessoa;
import com.senac.mybarber.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Pessoa user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}