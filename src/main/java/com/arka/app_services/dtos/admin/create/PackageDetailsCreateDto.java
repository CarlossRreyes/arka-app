package com.arka.app_services.dtos.admin.create;

import java.math.BigDecimal;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailsCreateDto {

    private String code;

    private Integer quantity;

    private BigDecimal base_price;



}
