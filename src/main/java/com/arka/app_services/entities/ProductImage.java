package com.arka.app_services.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.arka.app_services.constants.Auditable;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_product_image")
public class ProductImage extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "product_image_id" )
    private UUID product_image_id;
    
    @Column( length = 200, name = "image_path")
    private String image_path;
    
    @Column( name = "is_active")
    private Boolean is_active;

    // @CreationTimestamp
    // @Column( name = "created_at", nullable = false, updatable = false )
    // private LocalDateTime created_at;

    // @UpdateTimestamp
    // @Column( name = "updated_at" )
    // private LocalDateTime updated_at;
    
}
