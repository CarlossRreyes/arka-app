package com.arka.app_services.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arka.app_services.dtos.client.read.UserCliDto;
import com.arka.app_services.entities.User;



@Mapper( componentModel = "spring" )
public interface IUserMapper {

    @Mapping(target = "user_id", ignore = true)    
    @Mapping(target = "roles", ignore = true)    
    User toEntity( UserCliDto dto );


    
}
