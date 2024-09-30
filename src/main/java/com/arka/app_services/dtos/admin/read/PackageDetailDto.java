package com.arka.app_services.dtos.admin.read;


import java.util.Set;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailDto {

    private ProductDto product;

    // private BigDecimal base_price;

    private Set<String> pricings;

    private Integer quantity;



    

    

}
