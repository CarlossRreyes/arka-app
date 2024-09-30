package com.arka.app_services.dtos.client.read;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDetailCliDto {


    private ProductCliDto product;

    private Integer quantity;

}
