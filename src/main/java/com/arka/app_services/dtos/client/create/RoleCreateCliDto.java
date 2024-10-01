package com.arka.app_services.dtos.client.create;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateCliDto {   

    private String name;

    private String description;
    
    private Boolean state;

}
