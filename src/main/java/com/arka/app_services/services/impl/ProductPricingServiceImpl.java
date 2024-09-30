package com.arka.app_services.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.client.read.ProductPricingCliDto;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.mappers.IProductPricingMapper;
import com.arka.app_services.repositories.IProductPricingRepository;
import com.arka.app_services.repositories.IProductRepository;
import com.arka.app_services.services.IProductPricingService;

@Service
public class ProductPricingServiceImpl implements IProductPricingService {


    @Autowired
    private IProductPricingRepository productPricingRepository;  


    @Autowired
    private IProductPricingMapper iProductPricingMapper;

    @Override
    public ProductPricingCliDto findOneIsCurrent(String uuid) {
        try {                      
            var productPricing = productPricingRepository.findById( UUID.fromString( uuid ) )
                .orElseThrow( () -> new NotFoundException( "Pricing product not found with uuid " + uuid.toString()));
            return iProductPricingMapper.toCoDto( productPricing );
            
        } catch (DataAccessException e) {
            throw e;
        }


    }

}
