package com.senac.mybarber.model;

import java.util.Date;

import lombok.Data;

@Data
public class Usuario{

    private String login;
    private String nome;
    private Date tempoSessao;
    private String tokenJWT;
    private String perfil;
    private Long id;
}