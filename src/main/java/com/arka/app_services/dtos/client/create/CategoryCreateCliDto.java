package com.arka.app_services.dtos.client.create;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateCliDto {


    private String business_code;

    private String code;
  
    private String name;


    private String description;

    private String image_path;
    
    private Boolean is_active;


}
