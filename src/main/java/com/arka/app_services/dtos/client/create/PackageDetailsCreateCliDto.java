package com.arka.app_services.dtos.client.create;


import java.math.BigDecimal;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailsCreateCliDto {


    private String code;
    // private String product_id;

    private Integer quantity;

    private BigDecimal base_price;


    // private ProductCreateCliDto product;

    // private Integer quantity;

}
