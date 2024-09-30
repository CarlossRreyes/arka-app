package com.arka.app_services.services;

import java.util.List;

import com.arka.app_services.dtos.client.read.ProductPricingCliDto;




public interface IProductPricingService {

    public ProductPricingCliDto findOneIsCurrent ( String uuid );    

    

}
