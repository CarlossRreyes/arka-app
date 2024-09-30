package com.arka.app_services.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arka.app_services.services.IProductService;



@RestController
@RequestMapping("/administration/product/client")
public class ProductCliController {

    @Autowired
    private IProductService iProductService;



    @GetMapping( path = "/active" )
    public ResponseEntity<?> findAllActive( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( this.iProductService.findAllActive(  offset, limit), HttpStatus.OK);        
    }

    
}
