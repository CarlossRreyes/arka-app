package com.arka.app_services.dtos.admin.create;

import java.io.Serializable;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCreateDto implements Serializable {

    private String code;

    private String name;

    private String business_type_code;

    private String description;

    private String direction;

    private String image_path;

    


    

}
