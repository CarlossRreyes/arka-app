package com.arka.app_services.services;

import java.util.List;
import java.util.UUID;

import com.arka.app_services.dtos.admin.create.CategoryCreateDto;
import com.arka.app_services.dtos.admin.read.CategoryDto;
import com.arka.app_services.dtos.admin.update.CategoryUpdateDto;
import com.arka.app_services.dtos.client.read.CategoryCliDto;

import com.arka.app_services.entities.Category;



public interface ICategoryService {

    public CategoryDto findOneById(String category_id);

    public Category findOneByUuid( UUID uuid );

    public Category findOneByCode( String code );
    
    public List<CategoryDto> findAll( int limit, int offset );

    public List<CategoryCliDto> findAllActive( int limit, int offset );  

    public List<CategoryCliDto> findAllActiveByBusinessId( String business_id, int limit, int offset );  
    public List<Category> findAllActiveByBusinessUuid( UUID business_id ,int limit, int offset );  

    public Category updated( CategoryUpdateDto dto, String category_id );

    public Category create( CategoryCreateDto dto );


}


// https://stackoverflow.com/questions/61710510/mapstruct-cannot-find-implementation
