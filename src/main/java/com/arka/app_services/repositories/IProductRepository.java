package com.arka.app_services.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.arka.app_services.entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID > {

    @Query("Select p from Product p where p.is_available = true and p.code = ?1")
	public Optional<Product> findByCode(String code);



    
    // @Query( value = "CALL select_all_products();", nativeQuery = true )
    // @Query( value = "CALL selecte()", nativeQuery = true )
    // List<Product> findAllProducts ();

    // Page<Product> findAllProducts ( Pageable pageable );

    // @Query("Select p from Product p where p.is_available = true")
    // @Query("SELECT p FROM Product p JOIN p.pricings pp WHERE p.is_available = true AND pp.is_current = true")
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.pricings pp WHERE p.is_available = true AND pp.is_current = true")
    Page<Product> findByActive ( Pageable pageable );
    
}
