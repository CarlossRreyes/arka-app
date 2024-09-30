package com.arka.app_services.constants;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditable implements Serializable {

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column( name = "updated_at" )
    private LocalDateTime updated_at;
    
}