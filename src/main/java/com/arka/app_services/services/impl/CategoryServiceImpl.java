package com.arka.app_services.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.CategoryCreateDto;
import com.arka.app_services.dtos.admin.read.CategoryDto;
import com.arka.app_services.dtos.admin.update.CategoryUpdateDto;
import com.arka.app_services.dtos.client.read.CategoryCliDto;
import com.arka.app_services.entities.Category;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.mappers.ICategoryMapper;
import com.arka.app_services.repositories.ICategoryRepository;
import com.arka.app_services.services.ICategoryService;

import jakarta.transaction.Transactional;


@Service
public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private ICategoryMapper iCategoryMapper;

    @Override
    public Category findOneByUuid(UUID uuid) {        
        try {                       
            var category = iCategoryRepository.findById( uuid )
                .orElseThrow( () -> new NotFoundException( "Category not found with id " + uuid.toString()));
            return category;
            
        } catch (DataAccessException e) {
            throw e;
        }
        
        
    }
    
    @Override
    public CategoryDto findOneById(String category_id) {
        var uuid = verifyUUID(category_id);
        var category =  findOneByUuid( uuid );            
        return iCategoryMapper.toDto( category );

    }

    

    @Override
    public List<CategoryDto> findAll(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var categories = iCategoryRepository.findAll( pageRequest ).getContent();  

            return categories.stream().map( iCategoryMapper::toDto )
                .collect( Collectors.toList() );
               
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Transactional
    @Override
    public Category updated(CategoryUpdateDto dto, String category_id) {

        try {
            var categoryUpdate = findOneByUuid( UUID.fromString(category_id) );
            iCategoryMapper.updateToDomain(dto,  categoryUpdate );

            return iCategoryRepository.save( categoryUpdate );
            
        } catch (DataAccessException e) {
            throw e;
        }

    }
    
    @Transactional
    @Override
    public Category create(CategoryCreateDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    private UUID verifyUUID( String uuid ){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            System.err.println( "Error por ID: " + e.toString());
            throw e;
        }
    }

    @Override
    public Category findOneByCode(String code) {
        try {
            var category = iCategoryRepository.findByCode( code )
                .orElseThrow( () -> new NotFoundException(" Category not found by code " + code ));
            return category;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<CategoryCliDto> findAllActive(int limit, int offset) {

        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var categories = this.iCategoryRepository.findByActive( pageRequest ).getContent();  

            return categories.stream().map( iCategoryMapper::toCliDto )
                .collect( Collectors.toList() );            
               
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<CategoryCliDto> findAllActiveByBusinessId(String business_id, int limit, int offset) {
        var uuid = verifyUUID( business_id );
        var packages = findAllActiveByBusinessUuid( uuid, limit, offset );
        return packages.stream().map( iCategoryMapper::toCliDto )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Category> findAllActiveByBusinessUuid(UUID category_uuid, int limit, int offset) {
        try {          
            var packages = iCategoryRepository.findAllByBusinessId( category_uuid, limit, offset );
            if (packages.isEmpty()) {
                throw new NotFoundException("Packages not found with category id " + category_uuid.toString());
            }
            return packages;
            
        } catch (DataAccessException e) {
            throw e;
        }
           
    }
    
}


// https://mapstruct.org/faq/
