package com.arka.app_services.controllers.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.arka.app_services.dtos.client.read.CredentialsCliDto;
import com.arka.app_services.services.IAuthServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/access")
@RequiredArgsConstructor
public class AuthController {


    
    @Autowired
    private IAuthServices authServices;

    
    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid @RequestBody CredentialsCliDto dto ) {        
        String jwt = authServices.authenticate(dto); 
        return new ResponseEntity<>( jwt , HttpStatus.OK);
    }

    
}


//$2a$10$gHC5hJAy5C1tJYH4N10k7.I4aY8bFkcXq7SN4DZPfnF9/SmfU3b6K
//0a129440-87f5-4ea1-bd52-b4c0237abd04

