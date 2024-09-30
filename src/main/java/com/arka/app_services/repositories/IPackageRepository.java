package com.arka.app_services.repositories;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.Package;
import com.arka.app_services.entities.Product;

import jakarta.transaction.Transactional;

@Repository
public interface IPackageRepository extends JpaRepository< Package, UUID > {
    
    @Query( value = "SELECT * FROM get_packages_by_category(:category_uuid, :limit_count, :offset_count )", nativeQuery = true )
    List<Package> findByCategory(@Param("category_uuid") UUID category_uuid, @Param("limit_count") Integer limit_count, @Param("offset_count") Integer offset_count );
    

    @Query("Select p from Package p where p.is_available = true")
    Page<Package> findByActive ( Pageable pageable );
    // @Query("")
    // Page<Category> findAllByBusinessUuid ( Pageable pageable );

}





// CREATE OR REPLACE PROCEDURE public.get_packages_by_category_uuid(IN category_uuid uuid)
//  LANGUAGE plpgsql
// AS $procedure$
// BEGIN
//     SELECT 
//         p.package_id, 
//         p.total_units, 
//         p.total_price, 
//         p.is_promotion, 
//         p.is_available, 
//         p.created_at, 
//         p.updated_at
//     FROM 
//         tb_package AS p
//     JOIN 
//         tb_package_detail AS pd ON p.package_id = pd.package_id
//     JOIN 
//         tb_product AS pr ON pd.product_id = pr.product_id
//     JOIN 
//         tb_product_category AS pc ON pr.product_id = pc.product_id
//     JOIN 
//         tb_category AS c ON pc.category_id = c.category_id
//     WHERE c.category_id = category_uuid;
// END;
// $procedure$
