package com.arka.app_services.services;

import java.util.List;
import java.util.UUID;

import com.arka.app_services.dtos.admin.create.BusinessCreateDto;
import com.arka.app_services.dtos.admin.read.BusinessDto;
import com.arka.app_services.dtos.client.read.BusinessCliDto;
import com.arka.app_services.dtos.client.read.CategoryCliDto;
import com.arka.app_services.entities.Business;

public interface IBusinessService {

    public BusinessDto create( BusinessCreateDto dto );

    public Business findOneByUuid( UUID uuid );

    public Business findOneByCode( String code );


    public List<BusinessCliDto> findAllActive( int limit, int offset );  

}
