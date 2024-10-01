package com.arka.app_services.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.arka.app_services.dtos.admin.create.CategoryCreateDto;
import com.arka.app_services.dtos.admin.read.CategoryDto;
import com.arka.app_services.dtos.admin.update.CategoryUpdateDto;
import com.arka.app_services.dtos.client.create.CategoryCreateCliDto;
import com.arka.app_services.dtos.client.read.CategoryCliDto;
import com.arka.app_services.entities.Business;
import com.arka.app_services.entities.Category;



@Mapper( componentModel = "spring" )
public interface ICategoryMapper {

    @Mapping(target = "business", ignore = true)    
    Category toEntity( CategoryCreateDto dto );


    @Mapping(target = "business", ignore = true)    
    Category toEntity( CategoryCreateCliDto dto );

    
    
    @Mapping(target = "business", ignore = true)    
    void updateToDomain( CategoryUpdateDto dto, @MappingTarget Category category );
    

    @Mapping(target = "category_id", source = "category_id", qualifiedByName = "uuidToString")
    @Mapping(target = "business_id", source = "business", qualifiedByName = "businessToString")
    CategoryDto toDto(Category category);

    @Mapping(target = "category_id", source = "category_id", qualifiedByName = "uuidToString")
    @Mapping(target = "business_id", source = "business", qualifiedByName = "businessToString")
    CategoryCliDto toCliDto(Category category);


    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid.toString();
    }
    
    @Named("businessToString")
    static String businessToString(Business business) {
        return business.getBusiness_id().toString();
    }

}
