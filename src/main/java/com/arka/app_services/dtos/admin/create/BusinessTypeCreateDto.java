package com.arka.app_services.dtos.admin.create;

import java.io.Serializable;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTypeCreateDto implements Serializable {

    private String code;

    @Size( min = 5)
    @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String name;

    @Size( min = 10)
    @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String description;

    // private Boolean is_active;

}
