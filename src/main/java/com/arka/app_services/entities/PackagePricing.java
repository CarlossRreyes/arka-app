package com.arka.app_services.entities;



import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_package_pricing")
public class PackagePricing extends Auditable {
       
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "package_pricing_id" )
    private UUID package_pricing_id;
    
    @ManyToOne
    @JoinColumn(name = "package_detail_id")
    @JsonManagedReference
    private PackageDetail detail;

    @Column(name = "base_price", nullable = false )
    private BigDecimal base_price;

    @Column(name = "is_current", nullable = false )
    private Boolean is_current;

    @Column(name = "is_active", nullable = false )
    private Boolean is_active;

    // @CreationTimestamp
    // @Column( name = "created_at", nullable = false, updatable = false )
    // private LocalDateTime created_at;

    // @UpdateTimestamp
    // @Column( name = "updated_at" )
    // private LocalDateTime updated_at;
    
}