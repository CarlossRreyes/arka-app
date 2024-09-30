package com.arka.app_services.dtos.admin.read;

import java.math.BigDecimal;
import java.util.Set;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDto {


    private String package_id;

    private Integer total_units;

    private BigDecimal total_price;
    
    private Boolean isPromotion;

    private Set<PackageDetailDto> packageDetails;



}
