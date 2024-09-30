package com.arka.app_services.dtos.admin.update;

import java.util.Set;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageUpdateDto {


    private Boolean isPromotion;

    private Set<PackageDetailUpdateDto> details;



}
