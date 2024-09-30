package com.arka.app_services.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arka.app_services.services.IBusinessService;


@RestController
@RequestMapping("/administration/business/client")
public class BusinessCliController {



    @Autowired
    private IBusinessService businessService;

    @GetMapping( path = "/active" )
    public ResponseEntity<?> findAllActive( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( businessService.findAllActive(  offset, limit), HttpStatus.OK);        
    }




    
}
