package com.arka.app_services.dtos.admin.update;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailUpdateDto {


    private String product_id;

    private Integer quantity;

    private BigDecimal base_price;

}
