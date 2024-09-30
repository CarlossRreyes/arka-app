package com.arka.app_services.dtos.client.read;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCliDto {


    private String business_id;
    private String code;
    private String name;
    private String description;
    private String direction;
    private String image_path;
    private Boolean is_active;

    @JsonIgnore
    private Set<String> categories;

    @JsonIgnore
    private Set<String> products;
    
    private String business_type_id;

}
