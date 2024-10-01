package com.arka.app_services.dtos.client.create;

import java.io.Serializable;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTypeCreateCliDto implements Serializable {

    private String code;

    // @Size( min = 5)
    // @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String name;

    // @Size( min = 10)
    // @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String description;

    // private Boolean is_active;

}
