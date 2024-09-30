package com.arka.app_services.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arka.app_services.dtos.client.read.RoleCliDto;
import com.arka.app_services.entities.Role;




@Mapper( componentModel = "spring" )
public interface IRoleMapper {

    @Mapping(target = "privileges", ignore = true)    
    Role toEntity( RoleCliDto dto );


    
}
