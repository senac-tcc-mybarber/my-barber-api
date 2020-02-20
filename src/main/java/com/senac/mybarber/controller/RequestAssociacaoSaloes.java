package com.senac.mybarber.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAssociacaoSaloes {
     private List<Long> saloes;
}
