package com.arka.app_services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString( exclude = "pricings")
@EqualsAndHashCode( exclude = "pricings")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_package_detail")
public class PackageDetail extends Auditable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "package_detail_id" )
    private UUID package_detail_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;


    @OneToMany(mappedBy = "detail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference 
    private Set<PackagePricing> pricings;

    private BigDecimal base_price_units;

    @Column( name = "quantity")
    private Integer quantity;

    @Column(name = "is_included")
    private Boolean is_included;


}