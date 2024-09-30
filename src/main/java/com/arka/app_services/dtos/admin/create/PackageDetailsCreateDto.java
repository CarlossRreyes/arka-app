package com.arka.app_services.dtos.admin.create;

import java.math.BigDecimal;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailsCreateDto {

    private String code;
    // private String product_id;

    private Integer quantity;

    private BigDecimal base_price;



}
