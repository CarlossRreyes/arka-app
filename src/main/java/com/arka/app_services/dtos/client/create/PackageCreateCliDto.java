package com.arka.app_services.dtos.client.create;


import java.math.BigDecimal;
import java.util.Set;



import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageCreateCliDto {

    private Integer total_units;

    private BigDecimal total_price;
    
    private Boolean is_promotion;

    private Set<PackageDetailsCreateCliDto> details;

}
