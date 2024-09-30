package com.arka.app_services.dtos.client.read;





import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class UserCliDto {
   
    // private UUID user_id;
    
    @Email
    @NotNull
    private String email;
    
    @NotNull
    @Size( min = 6, max = 50 )
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "must have a Uppercase, lowercase letter and a number ")
    private String password;

    // @NotEmpty
    // private Set<@Valid UserRoleDto> roles;
    
    private Boolean state;

}
