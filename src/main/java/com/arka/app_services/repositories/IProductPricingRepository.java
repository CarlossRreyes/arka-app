package com.arka.app_services.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.ProductPricing;

@Repository
public interface IProductPricingRepository extends JpaRepository<ProductPricing, UUID> {

    // @Query("SELECT pp FROM ProductPricing pp WHERE pp.product_id = :productId AND pp.is_current = TRUE")
    // ProductPricing findCurrentPriceByProductId(@Param("productId") UUID productId);

}
