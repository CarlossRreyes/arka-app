package com.arka.app_services.dtos.client.read;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCliDto {


    private String product_id;
    private String business_id;
    private String code;

    private String name;
    
    
    private String description;
    
    
    private Integer units_in_stock;
    
    private Boolean is_available;

    private Set<String> categories;
    // private Set<ProductPricingCoDto> pricings;
    private BigDecimal price;
    
    private Set<String> images;

    
}

// private String product_id;
// private String business_id;
// private String code;

// private String name;


// private String description;


// private Integer units_in_stock;

// private Boolean is_available;

// private Set<String> categories;
// private Set<String> pricings;

// private Set<String> images;