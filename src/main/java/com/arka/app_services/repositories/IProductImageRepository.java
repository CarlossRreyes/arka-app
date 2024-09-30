package com.arka.app_services.repositories;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.arka.app_services.entities.ProductImage;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImage, UUID> {
    
    
}
