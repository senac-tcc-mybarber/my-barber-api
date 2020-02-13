package com.senac.mybarber.configuration;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.model.Usuario;

import io.jsonwebtoken.JwtBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 10 dias
    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse response, String username) throws IOException {
        Date tempoExpiracao = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(username);
        builder.setExpiration(tempoExpiracao);
        builder.signWith(SignatureAlgorithm.HS512, SECRET);
        String JWT = builder
                .compact();

        Usuario usuario = new Usuario();
        usuario.setLogin("login");
        usuario.setTempoSessao(tempoExpiracao);
        usuario.setPerfil("perfil");
        usuario.setTokenJWT(JWT);

        String json = new Gson().toJson(usuario);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        //response.addHeader(HEADER_STRING, TOKEN_PREFIX + JWT);
        // response.addCookie(new Cookie("TokenJWT", JWT));
        
    }

    static Authentication getAuthentication(HttpServletRequest request) {
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