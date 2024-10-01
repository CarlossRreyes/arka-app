package com.arka.app_services.mappers;

import org.mapstruct.Mapper;

import com.arka.app_services.dtos.admin.create.BusinessTypeCreateDto;
import com.arka.app_services.dtos.client.create.BusinessTypeCreateCliDto;
import com.arka.app_services.entities.BusinessType;



@Mapper( componentModel = "spring" )
public interface IBusinessTypeMapper {
    
    BusinessType toEntity( BusinessTypeCreateDto dto );


    BusinessType toEntity( BusinessTypeCreateCliDto dto );

}
