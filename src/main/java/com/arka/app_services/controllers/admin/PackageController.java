package com.arka.app_services.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arka.app_services.dtos.admin.update.PackageUpdateDto;
import com.arka.app_services.services.IPackageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/administration/package")
public class PackageController {
    
    @Autowired
    private IPackageService iPackageService;

    @GetMapping( path = "/{package_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOne( @PathVariable String package_id ){        
        return new ResponseEntity<>( iPackageService.findOneById( package_id ), HttpStatus.OK);        
    }

    @GetMapping( path = "/category/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOnebyCategory( @PathVariable String category_id,   @RequestParam( defaultValue = "10") Integer limit, @RequestParam( defaultValue = "0") Integer offset ){        
        return new ResponseEntity<>( iPackageService.findAllActiveByCategoryId( category_id, limit, offset ), HttpStatus.OK);        
    }

    @GetMapping()
    public ResponseEntity<?> findAll( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( iPackageService.findAll(  offset, limit), HttpStatus.OK);        
    }


    @PatchMapping( path = "/{package_id}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> patch( @PathVariable String package_id, @Valid @RequestBody PackageUpdateDto dto) {
        var product = iPackageService.updated(dto, package_id);
        return new ResponseEntity<>(  product , HttpStatus.OK);
    }






    

}
