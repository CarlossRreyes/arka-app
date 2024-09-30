package com.arka.app_services.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.repositories.IUserRepository;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        var user = iUserRepository.findOneByEmail( username ).orElseThrow( () -> new NotFoundException( "User not found with email: " + username )); 
        System.out.println( user );

        return new User( user.getUsername(), user.getPassword(), user.getAuthorities() );

    }



}
