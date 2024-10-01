package com.arka.app_services.dtos.client.create;





import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class UserCreateCliDto {
    
    @Email
    @NotNull
    private String email;
    
    @NotNull
    @Size( min = 6, max = 50 )
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "must have a Uppercase, lowercase letter and a number ")
    private String password;
 
    private Boolean state;

}
