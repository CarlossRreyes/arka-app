package com.arka.app_services.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.app_services.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID > {

    Optional<User> findOneByEmail( String email );

}
