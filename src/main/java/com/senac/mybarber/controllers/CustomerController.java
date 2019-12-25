package com.senac.mybarber.controllers;

import com.senac.mybarber.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Customer> createUser(@Valid @RequestBody Customer customer){

        //todo: criar método para inserir usuário no banco de dados, com condicionais para retornar status http diferentes.
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        return responseEntity;
    }

}
