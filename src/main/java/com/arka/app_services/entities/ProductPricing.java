package com.arka.app_services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;
import lombok.*;
// @Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString( exclude = "product")
@EqualsAndHashCode( exclude = "product")
@Entity
@Table(name = "tb_product_pricing")
public class ProductPricing extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "product_pricing_id" )
    private UUID product_pricing_id;

    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @Column(name = "base_price", nullable = false )
    private BigDecimal base_price;

    @Column(name = "is_current", nullable = false )
    private Boolean is_current;

    @Column(name = "is_active", nullable = false )
    private Boolean is_active;
    
}



// is_current: Indica si el precio es el precio actual del producto.
// is_active: Indica si el precio está activo y debe ser considerado en las consultas de precios.
// is_discounted: Indica si el precio incluye un descuento.
// is_promotional: Indica si el precio es parte de una promoción especial.