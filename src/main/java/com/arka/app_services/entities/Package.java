package com.arka.app_services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString( exclude = "packageDetails")
@EqualsAndHashCode( exclude = "packageDetails", callSuper = false )
@NoArgsConstructor
@Entity
@Table(name = "tb_package")
public class Package extends Auditable {

    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "package_id" )
    private UUID package_id;

    @Column( name = "total_units")
    private Integer total_units;
    
    @Column(name = "total_price")
    private BigDecimal total_price;

    @Column(name = "is_promotion")
    private Boolean is_promotion;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( name = "package_id" )
    @JsonManagedReference
    private Set<PackageDetail> packageDetails;
    
    @Column(name = "is_available")
    private Boolean is_available;
    
    
}

    // is_available: Indica si el paquete está disponible para la compra.
    // is_featured: Indica si el paquete es destacado.
    // is_discounted: Indica si el paquete tiene un descuento.
    // is_limited_edition: Indica si el paquete es de edición limitada.