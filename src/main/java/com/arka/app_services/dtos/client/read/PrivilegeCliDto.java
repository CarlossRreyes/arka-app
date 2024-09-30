package com.arka.app_services.dtos.client.read;



import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeCliDto {
   
    // private UUID privilege_id;

    private String name;

    private String description;
    
    private Boolean state;

}
