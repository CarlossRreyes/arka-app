package com.arka.app_services.dtos.admin.read;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private String product_id;
    private String business_id;
    private String code;

    private String name;
    
    // @NotBlank
    private String description;
    
    // @NotBlank
    private Integer units_in_stock;

    // @NotBlank
    private Boolean is_available;

    private Set<String> categories;
    private Set<String> pricings;
    
    private Set<String> images;

}
