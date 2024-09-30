package com.arka.app_services.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.BusinessCreateDto;
import com.arka.app_services.dtos.admin.read.BusinessDto;
import com.arka.app_services.dtos.client.read.BusinessCliDto;
import com.arka.app_services.entities.Business;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.mappers.IBusinessMapper;
import com.arka.app_services.repositories.IBusinessRepository;
import com.arka.app_services.repositories.ICategoryRepository;
import com.arka.app_services.services.IBusinessService;


@Service
public class BusinessServiceImpl implements IBusinessService {


    @Autowired
    private IBusinessRepository businessRepository;
    @Autowired
    private IBusinessMapper iBusinessMapper;

    @Override
    public BusinessDto create(BusinessCreateDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Business findOneByUuid(UUID uuid) {
        try {                       
            var business = businessRepository.findById( uuid )
                .orElseThrow( () -> new NotFoundException( "Business not found with id " + uuid.toString()));
            return business;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Business findOneByCode(String code) {
        try {
            var business = businessRepository.findByCode( code )
                .orElseThrow( () -> new NotFoundException(" Business not found by code " + code ));
            return business;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<BusinessCliDto> findAllActive(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var business = this.businessRepository.findAllByActive( pageRequest ).getContent();  

            return business.stream().map( iBusinessMapper::toCliDto )
                .collect( Collectors.toList() );            
               
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
}
