package com.arka.app_services.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.Business;
import com.arka.app_services.entities.Category;


@Repository
public interface IBusinessRepository extends JpaRepository< Business, UUID > {

    @Query("Select b from Business b where b.is_active = true and b.code = ?1")
	public Optional<Business> findByCode(String code);


    
    @Query("SELECT b from Business b where b.is_active = true ")
    Page<Business> findAllByActive ( Pageable pageable );

}
