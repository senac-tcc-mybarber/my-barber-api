package com.senac.mybarber.configuration;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.senac.mybarber.model.Pessoa;
import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.model.Usuario;

import com.senac.mybarber.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

    // EXPIRATION_TIME = 10 dias
    static final long EXPIRATION_TIME = 1999_000_000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    @Autowired
    PessoaRepository repository;

    void addAuthentication(HttpServletResponse response, String username) throws IOException {
        Date tempoExpiracao = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(username);
        builder.setExpiration(tempoExpiracao);
        builder.signWith(SignatureAlgorithm.HS512, SECRET);
        String JWT = builder.compact();

        Pessoa pessoa = repository.findByUsername(username);

        Usuario usuario = new Usuario();
        usuario.setId(pessoa.getId());
        usuario.setNome(pessoa.getNome());
        usuario.setLogin(pessoa.getUsername());
        usuario.setTempoSessao(tempoExpiracao);
        usuario.setTokenJWT(JWT);

        if (pessoa instanceof Profissional) {
            usuario.setPerfil("profissional");
        } else {
            usuario.setPerfil("cliente");
        }

        String json = new Gson().toJson(usuario);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        
    }

    Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // faz parse do token
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
}
