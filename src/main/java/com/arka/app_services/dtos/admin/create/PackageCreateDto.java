package com.arka.app_services.dtos.admin.create;

import java.math.BigDecimal;
import java.util.Set;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageCreateDto {


    private Integer total_units;

    private BigDecimal total_price;
    
    private Boolean is_promotion;

    private Set<PackageDetailsCreateDto> details;

}
