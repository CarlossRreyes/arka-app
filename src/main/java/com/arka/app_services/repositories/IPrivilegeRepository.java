package com.arka.app_services.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.Privilege;

@Repository
public interface IPrivilegeRepository extends JpaRepository<Privilege, UUID> {
    @Query("SELECT p FROM Privilege p WHERE p.name = ?1")
    public Privilege findByPrivilegeName( String name );
}
