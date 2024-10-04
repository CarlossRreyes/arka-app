package com.arka.app_services.mappers;


import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.arka.app_services.dtos.admin.create.PackageCreateDto;
import com.arka.app_services.dtos.admin.read.PackageDetailDto;
import com.arka.app_services.dtos.admin.read.PackageDto;
import com.arka.app_services.dtos.client.create.PackageCreateCliDto;
import com.arka.app_services.dtos.client.read.PackageCliDto;
import com.arka.app_services.entities.Package;
import com.arka.app_services.entities.PackageDetail;
import com.arka.app_services.entities.PackagePricing;



@Mapper( componentModel = "spring", uses = {IProductMapper.class, IConstantsMapper.class } )
public interface IPackageMapper {

    @Mapping( target = "packageDetails", ignore = true )
    Package toDomain ( PackageCreateDto dto );

    @Mapping( target = "packageDetails", ignore = true )
    Package toDomain ( PackageCreateCliDto dto );
    
    @Mapping( target = "package_id", source = "package_id", qualifiedByName = "uuidToString" )   
    PackageDto toDto ( Package domain );    
    
    @Mapping(target = "product", source = "product")
    @Mapping( target = "pricings", source = "pricings", qualifiedByName = "pricingToStringSet" )       
    PackageDetailDto toDto(PackageDetail detail);

    @Mapping(target = "package_id", source = "package_id", qualifiedByName = "uuidToString")
    PackageCliDto toCoDto ( Package domain );


    @Named("pricingToStringSet")
    default Set<String> imagesToStringSet(Set<PackagePricing> pricings) {
        return pricings.stream().map( price -> price.getPackage_pricing_id().toString()).collect(Collectors.toSet());
    }


}
