package com.arka.app_services.dtos.client.create;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateCliDto {



    @NotBlank
    private String business_code;
    
    private String code;

    private String name;
    
    @NotBlank
    private String description;

    @NotBlank
    private BigDecimal base_price;    
    
    @NotBlank
    private Integer units_in_stock;

    @NotBlank
    private Boolean is_available;

    private Set<String> categories;
    
    private Set<String> images;

    
}
