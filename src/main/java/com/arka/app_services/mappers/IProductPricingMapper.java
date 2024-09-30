package com.arka.app_services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
// import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.arka.app_services.dtos.admin.create.ProductPricingCreateDto;
import com.arka.app_services.dtos.admin.update.ProductPricingUpdateDto;
import com.arka.app_services.dtos.client.read.ProductPricingCliDto;
import com.arka.app_services.entities.Product;
import com.arka.app_services.entities.ProductPricing;

@Mapper( componentModel = "spring" )
public interface IProductPricingMapper {

    
    // @Mapping( target = "product_pricing_id", ignore = true )
    ProductPricing toEntity( ProductPricingCreateDto dto );

    @Mapping(target = "product_pricing_id", ignore = true)
    @Mapping(target = "product_id", source = "product", qualifiedByName = "productToString")
    ProductPricingCliDto toCoDto ( ProductPricing productPricing );


    void updatedToDomain(ProductPricingUpdateDto dto, @MappingTarget ProductPricing pricing );

    @Named("productToString")
    static String productToString(Product product) {
        return product.getProduct_id().toString();
    }
    
}
