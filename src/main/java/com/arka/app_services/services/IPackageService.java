package com.arka.app_services.services;

import java.util.List;
import java.util.UUID;

import com.arka.app_services.dtos.admin.create.PackageCreateDto;
import com.arka.app_services.dtos.admin.read.PackageDto;
import com.arka.app_services.dtos.admin.update.PackageUpdateDto;
import com.arka.app_services.dtos.client.read.PackageCliDto;
import com.arka.app_services.entities.Package;



public interface IPackageService {


    public List<PackageDto> findAll( int limit, int offset );

    public PackageDto findOneById(String package_id);

    public List<PackageCliDto> findAllActiveByCategoryId(String category_id, Integer limit, Integer offset);

    public Package findOneByUuid( UUID uuid );

    public List<Package> findOneByCategoryUuid( UUID uuid, Integer limit, Integer offset );
    // public List<Package> findOneByCategoryUuid( UUID uuid, Integer limit, Integer offset );

    public List<PackageCliDto> findAllActive( int limit, int offset );    

    

    public Package findOneByCode( String code );

    public PackageDto updated( PackageUpdateDto dto, String package_id );

    public PackageDto create( PackageCreateDto dto );


    



}
