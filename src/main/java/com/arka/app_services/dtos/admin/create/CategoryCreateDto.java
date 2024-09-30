package com.arka.app_services.dtos.admin.create;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto implements Serializable {

    // private String category_id;
    
    private String code;
    
    private String business_id;
    // @Size( min = 5)
    // @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String name;

    // @Size( min = 10)
    // @Pattern(regexp = "^[^0-9]*$", message = "must not be numeric")    
    private String description;

    private String image_path;
    
    // @Pattern(regexp = "true|false", message = "must be boolean")    
    private Boolean is_active;


}
