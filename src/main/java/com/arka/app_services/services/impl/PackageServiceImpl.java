package com.arka.app_services.services.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.PackageCreateDto;
import com.arka.app_services.dtos.admin.create.PackageDetailsCreateDto;
import com.arka.app_services.dtos.admin.read.PackageDetailDto;
import com.arka.app_services.dtos.admin.read.PackageDto;
import com.arka.app_services.dtos.admin.update.PackageDetailUpdateDto;
import com.arka.app_services.dtos.admin.update.PackageUpdateDto;
import com.arka.app_services.dtos.client.read.PackageCliDto;
import com.arka.app_services.entities.Package;
import com.arka.app_services.entities.PackageDetail;
import com.arka.app_services.entities.PackagePricing;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.mappers.IPackageMapper;
import com.arka.app_services.repositories.IPackageRepository;
import com.arka.app_services.services.IPackageService;
import com.arka.app_services.services.IProductService;

import jakarta.transaction.Transactional;

@Service
public class PackageServiceImpl implements IPackageService {

    @Autowired
    private IPackageMapper iPackageMapper;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IPackageRepository iPackageRepository;



    @Override
    public List<PackageDto> findAll(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var packages = iPackageRepository.findAll( pageRequest ).getContent();  

            return packages.stream().map( iPackageMapper::toDto )
                .collect( Collectors.toList() );
               
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public PackageDto updated(PackageUpdateDto dto, String package_id) {

        try {

            var total_units = dto.getDetails().stream().mapToInt( PackageDetailUpdateDto::getQuantity ).sum();

            var total_price = dto.getDetails().stream()
                .map( detail -> detail.getBase_price() )
                .reduce( BigDecimal.ZERO, BigDecimal::add);                
        
            var packageObj = findOneByUuid( UUID.fromString( package_id ));

            var existingProductMap = packageObj.getPackageDetails().stream()
                .collect( Collectors.toMap(
                    detail -> detail.getProduct().getProduct_id().toString(),  
                    details -> details
                ));

            System.out.println( existingProductMap );




            


            packageObj.setIs_promotion( dto.getIsPromotion() );
            packageObj.setTotal_price( total_price );
            packageObj.setTotal_units( total_units );


            var updatePackage = iPackageRepository.save( packageObj );
            var packateDto = iPackageMapper.toDto( updatePackage );
            return packateDto;
            


            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public PackageDto create(PackageCreateDto dto) {

        try {
            var total_units = dto.getDetails().stream().mapToInt( PackageDetailsCreateDto::getQuantity ).sum();

            var total_price = dto.getDetails().stream()
                .map( detail -> detail.getBase_price())
                .reduce( BigDecimal.ZERO, BigDecimal::add);                            
        
            
            var packageObj =  iPackageMapper.toDomain( dto );
            packageObj.setPackageDetails(
                dto.getDetails().stream().map( packageDto -> {

                    
                    var pricing = new HashSet<>(
                        Arrays.asList(
                            PackagePricing.builder()
                                .base_price( packageDto.getBase_price() )
                                .is_active( true )
                                .is_current( true )
                                .build()
                            
                        )
                    );

                    
                    var newDetails =  PackageDetail.builder()
                    .product( iProductService.findOneByCode( packageDto.getCode() ) )
                    .quantity( packageDto.getQuantity() )
                    // .base_price( packageDto.getBase_price() )
                    .pricings( pricing )
                    .build();
                    
                    pricing.stream()
                        .peek(price -> price.setDetail( newDetails ))
                        .collect( Collectors.toCollection( HashSet::new ));

                    return newDetails;


                }).collect( Collectors.toSet() )
            );
            packageObj.setTotal_units( total_units );
            packageObj.setTotal_price( total_price );
            packageObj.setIs_available( true );
        

            var newPackage = iPackageRepository.save( packageObj );
            var packateDto = iPackageMapper.toDto( newPackage );
            return packateDto;
            
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public PackageDto findOneById(String package_id) {
        var uuid = verifyUUID( package_id );
        var packageObj = findOneByUuid( uuid );
        return iPackageMapper.toDto( packageObj );
    }

    @Override
    public Package findOneByUuid(UUID uuid) {
        try {                      
            var packageObj = iPackageRepository.findById( uuid )
                .orElseThrow( () -> new NotFoundException( "Paclage not found with id " + uuid.toString()));
            return packageObj;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Package findOneByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOneByCode'");
    }


    private UUID verifyUUID( String uuid ){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {            
            throw e;
        }
    }

    @Override
    public List<PackageCliDto> findAllActiveByCategoryId(String category_id, Integer limit, Integer count) {
        var uuid = verifyUUID( category_id );
        var packages = findOneByCategoryUuid( uuid, limit, count );
        return packages.stream().map( iPackageMapper::toCoDto )
                .collect( Collectors.toList() );
    }

    @Transactional
    @Override
    public List<Package> findOneByCategoryUuid(UUID uuid, Integer limit, Integer count ) {
        try {          
            var packages = iPackageRepository.findByCategory( uuid, limit, count );

            // System.out.println( packages);

            if (packages.isEmpty()) {
                throw new NotFoundException("Packages not found with category id " + uuid.toString());
            }
                // .orElseThrow( () -> new NotFoundException( "Package not found with category id " + uuid.toString()));
            
            // return packages.stream().map( iPackageMapper::toDto )
            //     .collect( Collectors.toList() );
            // var packageObj = iPackageRepository.findByCategory( uuid )
            // return packages.stream().map( iPackageMapper::toDto )
            //     .collect( Collectors.toList() );
            // return packageObj;
            return packages;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<PackageCliDto> findAllActive(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var packages = this.iPackageRepository.findByActive( pageRequest ).getContent();  

            return packages.stream().map( iPackageMapper::toCoDto )
                .collect( Collectors.toList() );            
               
        } catch (DataAccessException e) {
            throw e;
        }
    }

}
