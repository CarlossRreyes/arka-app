package com.arka.app_services.services;

import java.util.UUID;

import com.arka.app_services.dtos.admin.create.BusinessTypeCreateDto;
import com.arka.app_services.dtos.admin.read.BusinessTypeDto;
import com.arka.app_services.entities.BusinessType;

public interface IBusinessTypeService {

    public BusinessTypeDto create( BusinessTypeCreateDto dto );

    public BusinessType findOneByUuid( UUID uuid );

    public BusinessType findOneByCode( String code );

}
