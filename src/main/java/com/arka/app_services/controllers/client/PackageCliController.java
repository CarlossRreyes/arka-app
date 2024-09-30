package com.arka.app_services.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.arka.app_services.services.IPackageService;



@RestController
@RequestMapping("/administration/package/client")
public class PackageCliController {
    
    @Autowired
    private IPackageService iPackageService;

    @GetMapping( path = "/active" )
    public ResponseEntity<?> findAllActive( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( iPackageService.findAllActive(  offset, limit), HttpStatus.OK);        
    }

    @GetMapping( path = "/category/active/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOnebyCategory( @PathVariable String category_id,   @RequestParam( defaultValue = "10") Integer limit, @RequestParam( defaultValue = "0") Integer offset ){        
        return new ResponseEntity<>( iPackageService.findAllActiveByCategoryId( category_id, limit, offset ), HttpStatus.OK);        
    }

}
