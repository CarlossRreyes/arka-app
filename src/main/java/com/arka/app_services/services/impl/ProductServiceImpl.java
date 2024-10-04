package com.arka.app_services.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.arka.app_services.dtos.admin.create.ProductCreateDto;
import com.arka.app_services.dtos.admin.create.ProductPricingCreateDto;
import com.arka.app_services.dtos.admin.read.ProductDto;
import com.arka.app_services.dtos.admin.update.ProductPricingUpdateDto;
import com.arka.app_services.dtos.admin.update.ProductUpdateDto;
import com.arka.app_services.dtos.client.read.ProductCliDto;
import com.arka.app_services.entities.Product;
import com.arka.app_services.entities.ProductImage;
import com.arka.app_services.entities.ProductPricing;
import com.arka.app_services.errors.exceptions.NotFoundException;
import com.arka.app_services.mappers.IProductMapper;
import com.arka.app_services.mappers.IProductPricingMapper;
import com.arka.app_services.repositories.IProductPricingRepository;
import com.arka.app_services.repositories.IProductRepository;
import com.arka.app_services.services.ICategoryService;
import com.arka.app_services.services.IProductService;

import jakarta.transaction.Transactional;


@Service
public class ProductServiceImpl implements IProductService {
    
    @Autowired
    private IProductMapper iproductMapper;

    @Autowired
    private IProductPricingMapper iProductPricingMapper;

    @Autowired
    private IProductRepository iProductRepository;    

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IProductPricingRepository iProductPricingRepository;

    @Transactional
    @Override
    public ProductDto updated(ProductUpdateDto productUpdateDto, String product_id) {        
        try {
            var product = findOneByUuid( UUID.fromString( product_id ) );
            iproductMapper.updatedToEntity(productUpdateDto, product);

            if( productUpdateDto.getCategories().isEmpty() || productUpdateDto.getCategories() == null ){
                product.getCategories().clear();
            }
            product.setCategories(
                    productUpdateDto.getCategories().stream().map( category_id ->
                        iCategoryService.findOneByUuid( UUID.fromString(category_id) )
                    ).collect(Collectors.toSet())
            );

            if( productUpdateDto.getProduct_pricing_id() != null ){
                UUID pricingId = UUID.fromString(productUpdateDto.getProduct_pricing_id());
                product.getPricings().forEach(pricing -> {
                    if (pricing.getProduct_pricing_id().equals(pricingId)) {
                        // pricing.setState(true);
                        // pricing.setState(true);
                    } else {
                        // pricing.setState(false);
                        // pricing.setState(false);
                    }
                });
                
            }

           
            if ( productUpdateDto.getCategories().isEmpty() || productUpdateDto.getCategories() == null ){
                product.getImages().clear();
            }
            product.getImages().clear();
            var images = productUpdateDto.getImages().stream().map( image -> 
                ProductImage.builder()                        
                    .image_path(image)
                    .is_active(true)
                    .build()
            ).collect( Collectors.toSet() );
            product.getImages().addAll( images );
    

            var productUpdated =  iProductRepository.save( product );
            var productDto = iproductMapper.toDto( productUpdated );
            return productDto;
        } catch (DataAccessException e) {
            throw e;
        }
        
        
    }

    @Override
    @Transactional
    public ProductDto create(ProductCreateDto productCreateDto ) {
        try {
            var product = iproductMapper.toEntity(productCreateDto);

            product.setCategories(
                productCreateDto.getCategories().stream().map( category_id ->
                    iCategoryService.findOneByUuid( UUID.fromString(category_id) )
                ).collect( Collectors.toSet() )
            );

            product.setImages(
                productCreateDto.getImages().stream().map( image -> 
                    ProductImage.builder()                        
                        .image_path(image)
                        .is_active(true)
                        .build()
                ).collect( Collectors.toSet() )
            );

            // product.setPricings(
            //     new HashSet<>( Arrays.asList(
            //         // new ProductPricing(
            //         //     null, 
            //         //     null, 
            //         //     productCreateDto.getBase_price(), 
            //         //     true, true, null, null)
            //         ProductPricing.builder()
            //             .base_price( productCreateDto.getBase_price() )
            //             .is_current( true )
            //             .is_active( true )
            //             .build()
            //     ))
            // );
                        
            var newProducto = iProductRepository.save( product ); 
            var productDto = iproductMapper.toDto( newProducto );
            return productDto;            
        } catch (DataAccessException e) {
            throw e;
        }
    }


    @Override
    public List<ProductDto> findAll(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var products = this.iProductRepository.findAll( pageRequest ).getContent();  

            return products.stream().map( iproductMapper::toDto )
                .collect( Collectors.toList() );
               
        } catch (DataAccessException e) {
            throw e;
        }
    }


    private ProductPricing priceFindOne( UUID id){
        ProductPricing pricing = iProductPricingRepository.findById(id)
            .orElseThrow( () -> new NotFoundException( "Product pricing not found with id " + id));
        return pricing;
    }

    private UUID verifyUUID( String uuid ){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {            
            throw e;
        }
    }


    @Override
    public ProductPricing createPricing(ProductPricingCreateDto dto) {
        
        try {              
            var product = findOneByUuid( UUID.fromString( dto.getProduct_id() ) );                 
            
            product.getPricings().stream()
            .filter( ProductPricing::getIs_current)
            .findFirst()
                .ifPresent( pricing -> {
                    pricing.setIs_current( false );
                    iProductPricingRepository.save( pricing );
                });
                
            var productPricing = ProductPricing.builder()
                .base_price( dto.getBase_price() )
                .is_active( true )
                .is_current( true )
                .product( product )
                
                .build();

        
                
            product.getPricings().add( productPricing );
            

            return iProductPricingRepository.save( productPricing );
            
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public ProductPricing updatePricing(ProductPricingUpdateDto dto, String product_pricing_id ) {
        UUID uuid = this.verifyUUID( product_pricing_id );
        try {
            var pricing = priceFindOne( uuid );
            iProductPricingMapper.updatedToDomain(dto, pricing );
            
            return iProductPricingRepository.save( pricing );
            
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public ProductDto findOneById(String product_id) {

        var uuid = verifyUUID(product_id);
        var product = findOneByUuid( uuid );
        return iproductMapper.toDto( product );
    }
    
    @Override
    public Product findOneByUuid( UUID uuid ) {
        try {                      
            var product = iProductRepository.findById( uuid )
                .orElseThrow( () -> new NotFoundException( "Product not found with id " + uuid.toString()));
            return product;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Product findOneByCode(String code) {
        try {
            var product = iProductRepository.findByCode( code ).orElseThrow( () -> new NotFoundException(" Product not found by code " + code ));
            return product;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ProductCliDto> findAllActive(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(limit, offset);
        try {            
                  
            var products = this.iProductRepository.findByActive( pageRequest ).getContent();  

            return products.stream().map( iproductMapper::toCliDto )
                .collect( Collectors.toList() );            
               
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
}


// https://stackoverflow.com/questions/582388/structuring-a-database-where-products-have-multiple-packaging-types-from-multipl?rq=4
