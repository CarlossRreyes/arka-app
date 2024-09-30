package com.arka.app_services.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arka.app_services.entities.Category;
import com.arka.app_services.entities.Product;

public interface ICategoryRepository extends JpaRepository<Category, UUID > {

    @Query("Select c from Category c where c.is_active = true and c.code = ?1")
	public Optional<Category> findByCode(String code);


    @Query("SELECT c from Category c where c.is_active = true ")
    Page<Category> findByActive ( Pageable pageable );

    // Page<Category> findAllByBusinessBusinessId(UUID businessId, Pageable pageable);

    // Page<Category> findAllByBusinessBusiness_idAndIsActiveTrue(UUID businessId, Pageable pageable);
    // Page<Category> findAllByBusinessBusinessIdAndIsActiveTrue(UUID businessId, Pageable pageable);

        
    // @Query( value = "SELECT * FROM get_packages_by_category(:category_uuid, :limit_count, :offset_count )", nativeQuery = true )
    // List<Package> findByCategory(@Param("category_uuid") UUID category_uuid, @Param("limit_count") Integer limit_count, @Param("offset_count") Integer offset_count );
    


    @Query( value = "SELECT * FROM get_categories_by_business(:business_uuid, :limit_count, :offset_count )", nativeQuery = true )
    List<Category> findAllByBusinessId(@Param("business_uuid") UUID business_uuid, @Param("limit_count") Integer limit_count, @Param("offset_count") Integer offset_count );
    
    

}
