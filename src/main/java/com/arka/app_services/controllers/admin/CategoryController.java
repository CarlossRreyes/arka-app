package com.arka.app_services.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import com.arka.app_services.dtos.admin.update.CategoryUpdateDto;
import com.arka.app_services.services.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/administration/category")
public class CategoryController {


    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping( path = "/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOne( @PathVariable String category_id ){        
        return new ResponseEntity<>( iCategoryService.findOneById(category_id), HttpStatus.OK);        
    }

    @GetMapping()
    public ResponseEntity<?> findAll( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( iCategoryService.findAll(  offset, limit), HttpStatus.OK);        
    }

    @PatchMapping( path = "/{category_id}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> patch( @PathVariable String category_id, @Valid @RequestBody CategoryUpdateDto dto) {
        var product = iCategoryService.updated(dto, category_id);
        return new ResponseEntity<>(  product , HttpStatus.OK);
    }



}
