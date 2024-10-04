package com.arka.app_services.mappers;

import java.util.UUID;

import org.mapstruct.Named;

public interface IConstantsMapper {



    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

}
