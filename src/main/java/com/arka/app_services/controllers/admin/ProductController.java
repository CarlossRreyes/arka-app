package com.arka.app_services.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arka.app_services.dtos.admin.create.ProductCreateDto;
import com.arka.app_services.dtos.admin.create.ProductPricingCreateDto;
import com.arka.app_services.dtos.admin.update.ProductPricingUpdateDto;
import com.arka.app_services.dtos.admin.update.ProductUpdateDto;
import com.arka.app_services.services.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/administration/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @GetMapping( path = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOne( @PathVariable String product_id ){        
        return new ResponseEntity<>( iProductService.findOneById( product_id ), HttpStatus.OK);        
    }

    @GetMapping()
    public ResponseEntity<?> findAll( @RequestParam( defaultValue = "0") int offset,  @RequestParam( defaultValue = "10") int limit ) {      
        return new ResponseEntity<>( this.iProductService.findAll(  offset, limit), HttpStatus.OK);        
    }


    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> create( @Valid @RequestBody ProductCreateDto dto) {
        var product = iProductService.create( dto );
        // System.out.println( dto );
        return new ResponseEntity<>( product , HttpStatus.CREATED); 
    }
    
    
    @PatchMapping( path = "/{product_id}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> patch( @PathVariable String product_id, @Valid @RequestBody ProductUpdateDto dto) {
        var product = iProductService.updated(dto, product_id);
        return new ResponseEntity<>(  product , HttpStatus.OK);
    }
            
    //*********************PRICE*******************/
    @PostMapping( path = "/pricing", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> createPricing( @Valid @RequestBody ProductPricingCreateDto dto) {
        var product = iProductService.createPricing( dto );        
        return new ResponseEntity<>( product , HttpStatus.CREATED); 
    }
    
    @PatchMapping( path = "/pricing/{product_pricing_id}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> patch( @PathVariable String product_pricing_id, @Valid @RequestBody ProductPricingUpdateDto dto) {
        var pricing = iProductService.updatePricing(dto, product_pricing_id);
        return new ResponseEntity<>(  pricing , HttpStatus.OK);
    }

    
}
