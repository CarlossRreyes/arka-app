package com.arka.app_services.dtos.admin.update;
import java.io.Serializable;

import java.util.Set;


import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto implements Serializable {



    // // @NotBlank
    private String code;

    private String name;
    
    // @NotBlank
    private String description;

    
    // @NotBlank
    private Integer units_in_stock;

    private String product_pricing_id;

    // @NotBlank
    private Boolean state;

    private Set<String> categories;
    
    private Set<String> images;


}
