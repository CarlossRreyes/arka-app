package com.arka.app_services.entities;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.math.BigDecimal;

import java.util.UUID;

import lombok.*;

@Builder
// @Data
@Getter
@Setter
@ToString( exclude = "detail")
@EqualsAndHashCode( exclude = "detail", callSuper = false )
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

    
}