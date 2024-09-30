package com.arka.app_services.dtos.client.read;


import java.math.BigDecimal;
import java.util.Set;



import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageCliDto {

    private String package_id;

    private Integer total_units;

    private BigDecimal total_price;
    
    private Boolean is_promotion;

    private Boolean is_available;


    private Set<PackageDetailCliDto> packageDetails;

}
