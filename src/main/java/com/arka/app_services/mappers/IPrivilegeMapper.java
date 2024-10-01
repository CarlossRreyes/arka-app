package com.arka.app_services.mappers;


import com.arka.app_services.dtos.client.create.PrivilegeCreateCliDto;
import com.arka.app_services.dtos.client.read.PrivilegeCliDto;
import com.arka.app_services.entities.Privilege;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper( componentModel = "spring" )
public interface IPrivilegeMapper {

    @Mapping(target = "privilege_id", ignore = true)        
    Privilege toEntity( PrivilegeCliDto dto );

    @Mapping(target = "privilege_id", ignore = true)        
    Privilege toEntity( PrivilegeCreateCliDto dto );


    
}