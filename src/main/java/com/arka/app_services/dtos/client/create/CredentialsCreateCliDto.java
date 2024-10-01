package com.arka.app_services.dtos.client.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CredentialsCreateCliDto {

    @Email
    @NotNull
    private String email;
    
    
    @NotNull
    @Size( min = 6, max = 50 )
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "must have a Uppercase, lowercase letter and a number ")
    private String password;

}
