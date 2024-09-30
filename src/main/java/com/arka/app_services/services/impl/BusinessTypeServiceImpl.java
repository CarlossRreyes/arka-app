package com.arka.app_services.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.BusinessTypeCreateDto;
import com.arka.app_services.dtos.admin.read.BusinessTypeDto;
import com.arka.app_services.entities.BusinessType;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.repositories.IBusinessRepository;
import com.arka.app_services.repositories.IBusinessTypeRepository;
import com.arka.app_services.services.IBusinessTypeService;



@Service
public class BusinessTypeServiceImpl implements IBusinessTypeService {

    @Autowired
    private IBusinessTypeRepository businessTypeRepository;

    @Override
    public BusinessTypeDto create(BusinessTypeCreateDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public BusinessType findOneByUuid(UUID uuid) {
        try {                       
            var businessType = businessTypeRepository.findById( uuid )
                .orElseThrow( () -> new NotFoundException( "Business type not found with id " + uuid.toString()));
            return businessType;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public BusinessType findOneByCode(String code) {
        try {
            var businessType = businessTypeRepository.findByCode( code )
                .orElseThrow( () -> new NotFoundException("Business type not found by code " + code ));
            return businessType;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
}
