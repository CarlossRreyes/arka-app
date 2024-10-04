package com.arka.app_services.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.PackageDetailsCreateDto;
import com.arka.app_services.dtos.client.create.PackageDetailsCreateCliDto;
import com.arka.app_services.entities.PackageDetail;
import com.arka.app_services.entities.PackagePricing;
import com.arka.app_services.entities.Privilege;
import com.arka.app_services.entities.ProductImage;
import com.arka.app_services.entities.ProductPricing;
import com.arka.app_services.entities.Role;
import com.arka.app_services.errors.exceptions.ConflictException;
import com.arka.app_services.mappers.IBusinessMapper;
import com.arka.app_services.mappers.IBusinessTypeMapper;
import com.arka.app_services.mappers.ICategoryMapper;
import com.arka.app_services.mappers.IPackageMapper;
import com.arka.app_services.mappers.IPrivilegeMapper;
import com.arka.app_services.mappers.IProductMapper;
import com.arka.app_services.mappers.IRoleMapper;
import com.arka.app_services.mappers.IUserMapper;
import com.arka.app_services.repositories.IBusinessRepository;
import com.arka.app_services.repositories.IBusinessTypeRepository;
import com.arka.app_services.repositories.ICategoryRepository;
import com.arka.app_services.repositories.IPackageDetailRepository;
import com.arka.app_services.repositories.IPackagePricingRepository;
import com.arka.app_services.repositories.IPackageRepository;
import com.arka.app_services.repositories.IPrivilegeRepository;
import com.arka.app_services.repositories.IProductImageRepository;
import com.arka.app_services.repositories.IProductPricingRepository;
import com.arka.app_services.repositories.IProductRepository;
import com.arka.app_services.repositories.IRoleRepository;
import com.arka.app_services.repositories.IUserRepository;
import com.arka.app_services.services.IBusinessService;
import com.arka.app_services.services.IBusinessTypeService;
import com.arka.app_services.services.ICategoryService;
import com.arka.app_services.services.IPackageService;
import com.arka.app_services.services.IProductService;
import com.arka.app_services.services.ISeedService;
import com.arka.app_services.utils.seed.SeedData;
import jakarta.transaction.Transactional;


@Service
public class SeedServiceImpl implements ISeedService {

    @Autowired
    private SeedData seedData;

    //***************Mapper****************/
    @Autowired
    private ICategoryMapper iCategoryMapper;

    @Autowired
    private IPackageMapper iPackageMapper;

    @Autowired
    private IBusinessMapper businessMapper;

    @Autowired
    private IBusinessTypeMapper iBusinessTypeMapper;

    @Autowired
    private IProductMapper iProductMapper;   

    @Autowired
    private IRoleMapper iRoleMapper; 

    @Autowired
    private IPrivilegeMapper iPrivilegeMapper; 

    @Autowired
    private IUserMapper iUserMapper;   


    //**************Repositories*************
    @Autowired
    private IPackageRepository iPackageRepository;
    @Autowired
    private IPackageDetailRepository packageDetailRepository;

    @Autowired
    private IPackageService iPackageService;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductImageRepository productImageRepository;

    @Autowired
    private IProductPricingRepository productPricingRepository;

    @Autowired
    private IPackagePricingRepository iPackagePricingRepository;

    @Autowired
    private IBusinessRepository businessRepository;

    @Autowired
    private IBusinessTypeRepository businessTypeRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPrivilegeRepository privilegeRepository;

    @Autowired
    private IUserRepository userRepository;




    //*****************Services*******************/
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IBusinessService businessService;

    @Autowired
    private IBusinessTypeService businessTypeService;




    @Override
    public void runSeed() {

        this.deleteUserRole();
        this.deleteRolePrivilege();


        this.deleteRole();
        this.deletePrivilege();

        this.deleteUser();

        
        this.deleteAllImageProducts();
        this.deleteAllCategoriesProducts();
                                
        this.deteleAllPackageDetailsProduct(); 






        this.deleteAllProductPricing();

        this.deleteAllPackageDetails();
        this.deleteAllPackagePricing();
        
        this.deleteAllBusinessType();
        this.deleteAllPackages();
        this.deleteAllCategories();
        this.deleteAllProducts();
        this.deleteAllBusiness();
        
        this.insertBusinessType();
        this.insertBusiness();
        
        this.insertCategories();
        this.insertProducts();
        this.insertPackages();

        this.insertRoles();
        this.insertPrivileges();
        this.insertUsers();
        
    }

    @Override
    @Transactional
    public void insertCategories() {

        try {
            seedData.getCategories().stream().forEach( categoryDto -> {
                var newCategory = iCategoryMapper.toEntity( categoryDto );
                var business = businessService.findOneByCode( categoryDto.getBusiness_code() );
                
                newCategory.setBusiness( business );
                categoryRepository.save( newCategory );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public void insertProducts() {

        try {
            
            seedData.getProducts().stream().forEach( productDto -> {
                             
                var pricings = new HashSet<>(                    
                            Arrays.asList(
                                ProductPricing.builder()
                                    .base_price( productDto.getBase_price() )                                    
                                    .is_current( true )
                                    .is_active( true )
                                    .build()
                            )
                );                
                
                var newProduct = iProductMapper.toCliEntity( productDto );
                
                var business = businessService.findOneByCode( productDto.getBusiness_code() );
                newProduct.setBusiness( business );
                
                var categories = productDto.getCategories().stream().map( code -> 
                    iCategoryService.findOneByCode( code )
                ).collect( Collectors.toSet() );


                
                newProduct.setCategories( categories );

                var images = productDto.getImages().stream().map( image -> 
                        ProductImage.builder()                        
                            .image_path(image)
                            .is_active(true)
                            .build()
                    ).collect( Collectors.toSet() );
                    
                newProduct.setImages( images );

                newProduct.setPricings( pricings );                

                pricings.stream()
                    .peek(price -> price.setProduct(newProduct))
                    .collect( Collectors.toCollection( HashSet::new ));

                

                productRepository.save( newProduct );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllImageProducts() {

        try {
            productImageRepository.deleteAll();            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllCategoriesProducts() {
        try {
            var products = productRepository.findAll();
    
            products.stream().forEach( product -> {
                product.getCategories().clear();
                productRepository.save( product );
            });                     
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllCategories() {
        try {
            categoryRepository.deleteAll();
        } catch ( DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllProducts() {
        try {
            productRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteAllProductPricing() {

        try {
            productPricingRepository.deleteAll();                 
        } catch (DataAccessException e) {
            throw e;
        }
        
    }

    @Override
    public void deleteAllPackageDetails() {
        try {
            var packages = iPackageRepository.findAll();
    
            packages.forEach( packageObj -> {
                packageObj.getPackageDetails().clear();
                
                iPackageRepository.save( packageObj );
            });                     
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void insertPackages() {


        try {
            
            seedData.getPackages().stream().forEach( dto -> {
                
                var total_units = dto.getDetails().stream().mapToInt( PackageDetailsCreateCliDto::getQuantity ).sum();
    
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
                        
                        BigDecimal total_units_bd = BigDecimal.valueOf(total_units);

                        
                        var base_price_by_units = total_price.divide(total_units_bd, 2, RoundingMode.HALF_UP);  // 2 es la cantidad de decimales

                        
                        var newDetails =  PackageDetail.builder()
                        .product( iProductService.findOneByCode( packageDto.getCode() ) )
                        .quantity( packageDto.getQuantity() )
                        .is_included( true )                                                

                        .base_price_units( base_price_by_units )
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
            
    
                iPackageRepository.save( packageObj );                
            });
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllPackages() {
        try {
            iPackageRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
    }

    @Override
    public void deleteAllPackagePricing() {
        
        try {
            iPackagePricingRepository.deleteAll();                 
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void insertBusinessType() {
        try {
            seedData.getBusinessTypes().stream().forEach( businessTypesDto -> {
                var newBusinessType = iBusinessTypeMapper.toEntity( businessTypesDto );
                newBusinessType.setIs_active( true );
                businessTypeRepository.save( newBusinessType );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllBusinessType() {
        try {
            businessTypeRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
    }

    @Override
    public void insertBusiness() {
        try {
            seedData.getBusinesses().stream().forEach( businessDto -> {
                var newBusiness = businessMapper.toEntity( businessDto );

                var businessType = businessTypeService.findOneByCode( businessDto.getBusiness_type_code() );
                newBusiness.setBusinessType( businessType );

                newBusiness.setIs_active( true );            
                businessRepository.save( newBusiness );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteAllBusiness() {
        try {
            businessRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
    }

    @Override
    public void insertRoles() {
        try {
            seedData.getRoles().stream().forEach( roleDto -> {
                var newRoles = iRoleMapper.toEntity( roleDto );
        
                roleRepository.save( newRoles );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void insertPrivileges() {
        try {
            seedData.getPrivileges().stream().forEach( privilegeDto -> {
                var newPrivilege = iPrivilegeMapper.toEntity( privilegeDto );
        
                privilegeRepository.save( newPrivilege );
            });                    
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void insertUsers() {
        try {
            seedData.getUsers().stream().forEach( userDto -> {
                var newUsers = iUserMapper.toEntity( userDto );
                newUsers.setPassword( new BCryptPasswordEncoder().encode( userDto.getPassword() ) );
                Set<Privilege> privileges = new HashSet<>();
                Set<Role> roles = new HashSet<>();
                //Roles and privileges
                var role =  roleRepository.findByRoleName("ADMIN");
                // var role2 =  roleRepository.findByRoleName("CLIENT");
    
                var privilege1 = privilegeRepository.findByPrivilegeName("READ_PRIVILEGE");
                var privilege2 = privilegeRepository.findByPrivilegeName("WRITE_PRIVILEGE");
                privileges.add( privilege1 );
                privileges.add( privilege2 );

                role.setPrivileges(privileges);

                roles.add( role );
                newUsers.setRoles(roles);

                          
                userRepository.save( newUsers );
            });                    
        } catch (DataAccessException e) {
                  if( e instanceof DataIntegrityViolationException ){

                throw new ConflictException("Este usuario ya existe!");
                
            }
        }
    }

    @Override
    public void deleteUserRole() {
        try {
            var users = userRepository.findAll();
    
            users.stream().forEach( user -> {
                user.getRoles().clear();
                user.getAuthorities().clear();
                userRepository.save( user );
            });                     
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public void deleteRolePrivilege() {
        try {
            var roles = roleRepository.findAll();
    
            roles.stream().forEach( role -> {
                role.getPrivileges().clear();                
                roleRepository.save( role );
            });                     
        } catch (DataAccessException e) {
            throw e;
        }       
    }

    @Override
    public void deleteRole() {
        try {
            roleRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
    }

    @Override
    public void deletePrivilege() {
        try {
            privilegeRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
    }
    
    @Override
    public void deleteUser() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            throw e;
        } 
       
    }

    @Override
    public void deteleAllPackageDetailsProduct() {
        try {
            var packagesDetails = packageDetailRepository.findAll();
    
            packagesDetails.stream().forEach( details -> {
                details.setProduct(null);                    
                // packageDetailRepository.deleteAll();
            });                     
        } catch (DataAccessException e) {
            throw e;
        }  
    }
    
}
