package com.arka.app_services.services;

import com.arka.app_services.dtos.client.read.CredentialsCliDto;
import com.arka.app_services.dtos.client.read.UserCliDto;

public interface IAuthServices {


    public String authenticate( CredentialsCliDto credentialDto );
    
    public void register( UserCliDto userDto );

}
