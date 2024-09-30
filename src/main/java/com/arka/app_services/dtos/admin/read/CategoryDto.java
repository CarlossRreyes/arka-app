package com.arka.app_services.dtos.admin.read;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String category_id;

    private String business_id;

    private String code;
  
    private String name;


    private String description;

    private String image_path;
    
    private Boolean is_active;



}
