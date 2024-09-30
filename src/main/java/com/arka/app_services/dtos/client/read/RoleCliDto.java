package com.arka.app_services.dtos.client.read;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCliDto {
   
    // private UUID role_id;

    private String name;

    private String description;
    
    private Boolean state;

}
