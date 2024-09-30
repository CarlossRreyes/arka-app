package com.arka.app_services.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;


import com.arka.app_services.services.ICategoryService;

@RestController
@RequestMapping("/administration/category/client")
public class CategoryCliController {


    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping( path = "/active" )
    public ResponseEntity<?> findAllActive( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( iCategoryService.findAllActive(  offset, limit), HttpStatus.OK);        
    }

    @GetMapping( path = "/business/active/{business_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOnebyCategory( @PathVariable String business_id,   @RequestParam( defaultValue = "10") Integer limit, @RequestParam( defaultValue = "0") Integer offset ){        
        return new ResponseEntity<>( iCategoryService.findAllActiveByBusinessId( business_id, limit, offset ), HttpStatus.OK);        
    }


}
