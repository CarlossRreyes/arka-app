package com.arka.app_services.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.arka.app_services.entities.BusinessType;

@Repository
public interface IBusinessTypeRepository extends JpaRepository< BusinessType, UUID > {

    @Query("Select b from BusinessType b where b.is_active = true and b.code = ?1")
	public Optional<BusinessType> findByCode(String code);

}
