package com.arka.app_services.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.client.read.CredentialsCliDto;
import com.arka.app_services.dtos.client.read.UserCliDto;
import com.arka.app_services.errors.exceptions.UnauthorizedException;
import com.arka.app_services.repositories.IPrivilegeRepository;
import com.arka.app_services.repositories.IRoleRepository;
import com.arka.app_services.repositories.IUserRepository;
import com.arka.app_services.services.IAuthServices;
import com.arka.app_services.services.jwt.JwtService;

import org.springframework.security.core.AuthenticationException;


@Service
public class AuthServicesImpl implements IAuthServices {


    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private IPrivilegeRepository iPrivilegeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String authenticate(CredentialsCliDto credentialDto) {
        try {
            var userDetails = userDetailsService.loadUserByUsername( credentialDto.getEmail() );
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialDto.getEmail(), credentialDto.getPassword())
            );  
            
            System.out.println("******USER DETAILS SERVICE: " + userDetails.toString() );
            return jwtService.generateToken( userDetails );
                
        } catch (AuthenticationException e) {            
            throw new UnauthorizedException(e.getMessage());
        }      
    }

    @Override
    public void register(UserCliDto userDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}
