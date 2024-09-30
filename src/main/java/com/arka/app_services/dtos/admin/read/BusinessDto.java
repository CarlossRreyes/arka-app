package com.arka.app_services.dtos.admin.read;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {

    private String name;

    private String description;

    private String direction;

    private String image_path;

}
