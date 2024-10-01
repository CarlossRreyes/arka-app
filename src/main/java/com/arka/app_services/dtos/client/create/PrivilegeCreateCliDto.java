package com.arka.app_services.dtos.client.create;



import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeCreateCliDto {

    private String name;

    private String description;
    
    private Boolean state;

}
