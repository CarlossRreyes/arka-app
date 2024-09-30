package com.arka.app_services.services;
import java.util.List;
import java.util.UUID;

import com.arka.app_services.dtos.admin.create.ProductCreateDto;
import com.arka.app_services.dtos.admin.create.ProductPricingCreateDto;
import com.arka.app_services.dtos.admin.read.ProductDto;
import com.arka.app_services.dtos.admin.update.ProductPricingUpdateDto;
import com.arka.app_services.dtos.admin.update.ProductUpdateDto;
import com.arka.app_services.dtos.client.read.ProductCliDto;
import com.arka.app_services.entities.Product;
import com.arka.app_services.entities.ProductPricing;



public interface IProductService {


    public ProductDto findOneById(String product_id);

    public Product findOneByUuid( UUID uuid );

    public Product findOneByCode( String code );

    public List<ProductDto> findAll( int limit, int offset );    

    public List<ProductCliDto> findAllActive( int limit, int offset );    

    public ProductDto updated( ProductUpdateDto dto, String product_id );

    public ProductDto create( ProductCreateDto dto );

    public ProductPricing createPricing( ProductPricingCreateDto dto );
    
    public ProductPricing updatePricing( ProductPricingUpdateDto dto, String product_pricing_id );


}

