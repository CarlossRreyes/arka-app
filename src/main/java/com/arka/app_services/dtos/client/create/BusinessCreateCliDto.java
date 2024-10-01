package com.arka.app_services.dtos.client.create;




import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCreateCliDto {


    private String code;

    private String name;

    private String business_type_code;

    private String description;

    private String direction;

    private String image_path;


}
