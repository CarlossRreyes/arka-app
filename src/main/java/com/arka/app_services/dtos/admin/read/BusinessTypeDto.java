package com.arka.app_services.dtos.admin.read;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTypeDto {

    private String category_id;

    private String code;
  
    private String name;


    private String description;



}
