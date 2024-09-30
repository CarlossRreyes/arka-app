package com.arka.app_services.mappers;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.arka.app_services.dtos.admin.create.BusinessCreateDto;
import com.arka.app_services.dtos.client.read.BusinessCliDto;
import com.arka.app_services.entities.Business;
import com.arka.app_services.entities.BusinessType;
import com.arka.app_services.entities.Category;
import com.arka.app_services.entities.Product;



@Mapper( componentModel = "spring" )
public interface IBusinessMapper {

    // @Mapping(target = "categories", ignore = true)
    Business toEntity( BusinessCreateDto dto );


    @Mapping(target = "business_id", source = "business_id", qualifiedByName = "uuidToString")
    @Mapping(target = "business_type_id", source = "businessType", qualifiedByName = "businessTypeToString")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoriesToStringSet")
    @Mapping(target = "products", source = "products", qualifiedByName = "productsToStringSet")
    BusinessCliDto toCliDto ( Business business );


    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("businessTypeToString")
    static String businessTypeToString(BusinessType businessType) {
        return businessType.getBusiness_type_id().toString();
    }

    @Named("categoriesToStringSet")
    default Set<String> categoriesToStringSet(Set<Category> categories) {
        return categories.stream().map(category -> category.getCategory_id().toString()).collect(Collectors.toSet());
    }


    @Named("productsToStringSet")
    default Set<String> productsToStringSet(Set<Product> products) {
        return products.stream().map(product -> product.getProduct_id().toString()).collect(Collectors.toSet());
    }


}
