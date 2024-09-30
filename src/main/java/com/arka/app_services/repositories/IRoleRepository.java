package com.arka.app_services.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.Role;


@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID > {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByRoleName( String name );
    
}
