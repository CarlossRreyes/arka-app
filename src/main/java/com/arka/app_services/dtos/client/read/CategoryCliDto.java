package com.arka.app_services.dtos.client.read;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCliDto {


    private String category_id;

    private String business_id;

    private String code;
  
    private String name;


    private String description;

    private String image_path;
    
    private Boolean is_active;


}
