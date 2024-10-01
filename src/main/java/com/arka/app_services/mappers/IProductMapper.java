package com.arka.app_services.mappers;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.arka.app_services.dtos.admin.create.ProductCreateDto;
import com.arka.app_services.dtos.admin.read.ProductDto;
import com.arka.app_services.dtos.admin.update.ProductUpdateDto;
import com.arka.app_services.dtos.client.create.ProductCreateCliDto;
import com.arka.app_services.dtos.client.read.ProductCliDto;
import com.arka.app_services.dtos.client.read.ProductPricingCliDto;
// import com.arka.app_services.dtos.consumer.read.ProductCoDto;
// import com.arka.app_services.dtos.consumer.read.ProductPricingCoDto;
import com.arka.app_services.entities.Business;
import com.arka.app_services.entities.Category;
import com.arka.app_services.entities.Product;
import com.arka.app_services.entities.ProductImage;
import com.arka.app_services.entities.ProductPricing;


@Mapper( componentModel = "spring" )
public interface IProductMapper {

    // @Mapping(target = "product_id", ignore = true)

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "pricings", ignore = true)
    @Mapping(target = "images", ignore = true)   
    @Mapping(target = "business", ignore = true)   
    Product toEntity(ProductCreateDto dto);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "pricings", ignore = true)
    @Mapping(target = "images", ignore = true)   
    @Mapping(target = "business", ignore = true)   
    Product toEntity(ProductCreateCliDto dto);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "pricings", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "product_id", ignore = true)
    void updatedToEntity(ProductUpdateDto dto, @MappingTarget Product product);

    @Mapping(target = "images", source = "images", qualifiedByName = "imagesToStringSet")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoriesToStringSet")
    @Mapping(target = "pricings", source = "pricings", qualifiedByName = "pricingsToStringSet")
    @Mapping(target = "product_id", source = "product_id", qualifiedByName = "uuidToString")
    @Mapping(target = "business_id", source = "business", qualifiedByName = "businessToString")
    ProductDto toDto(Product product);
    
    // @Mapping(target = "pricings", source = "pricings", qualifiedByName = "pricingsToStringSet")
    
    // @Mapping(target = "pricings", source = "pricings")
    @Mapping(target = "images", source = "images", qualifiedByName = "imagesToStringSet")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoriesToStringSet")
    @Mapping(target = "product_id", source = "product_id", qualifiedByName = "uuidToString")
    @Mapping(target = "business_id", source = "business", qualifiedByName = "businessToString")
    @Mapping(target = "price", source = "pricings", qualifiedByName = "getCurrentPrice")
    // @Mapping(target = "pricings", source = "pricings", qualifiedByName = "pricingsToDtoSet")
    ProductCliDto toCoDto ( Product product );
    

    @Named("imagesToStringSet")
    default Set<String> imagesToStringSet(Set<ProductImage> images) {
        return images.stream().map(ProductImage::getImage_path).collect(Collectors.toSet());
    }

    @Named("categoriesToStringSet")
    default Set<String> categoriesToStringSet(Set<Category> categories) {
        return categories.stream().map(category -> category.getCategory_id().toString()).collect(Collectors.toSet());
    }

    @Named("pricingsToStringSet")
    default Set<String> pricingsToStringSet(Set<ProductPricing> pricings) {
        return pricings.stream().map(pricing -> pricing.getProduct_pricing_id().toString()).collect(Collectors.toSet());
    }

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("businessToString")
    static String businessToString(Business business) {
        return business.getBusiness_id().toString();
    }




    @Named("pricingsToDtoSet")
    default Set<ProductPricingCliDto> pricingsToDtoSet(Set<ProductPricing> pricings) {
        return pricings.stream().map(this::productPricingToDto).collect(Collectors.toSet());
    }



    @Mapping(target = "product_pricing_id", source = "product_pricing_id", qualifiedByName = "uuidToString")
    ProductPricingCliDto productPricingToDto(ProductPricing pricing);
    
    @Named("getCurrentPrice")
    default BigDecimal getCurrentPrice(Set<ProductPricing> pricings) {
        return pricings.stream()
            .filter(ProductPricing::getIs_current)  // Filter by is_current = true
            .findFirst()
            .map(ProductPricing::getBase_price)  // Get the base price
            .orElse(null);  // Return null if no current pricing is found
    }
    



}
