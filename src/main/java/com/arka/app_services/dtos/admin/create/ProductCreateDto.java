package com.arka.app_services.dtos.admin.create;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto implements Serializable {

    // @NotBlank
    private String business_code;
    
    private String code;

    private String name;

    
    // @NotBlank
    private String description;

    // @NotBlank
    private BigDecimal base_price;    

    // @NotBlank
    // private Double discount_price;
    
    // @NotBlank
    private Integer units_in_stock;

    // @NotBlank
    private Boolean is_available;

    private Set<String> categories;
    
    private Set<String> images;


}
