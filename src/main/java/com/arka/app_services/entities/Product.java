package com.arka.app_services.entities;


import java.util.Set;
import java.util.UUID;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString( exclude = "pricings")
@EqualsAndHashCode( exclude = "pricings", callSuper = false )
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "product_id" )
    private UUID product_id;
    
    @Column( length = 10, name = "code")
    private String code;

    @Column( length = 80, name = "name")
    private String name;

    @Column( length = 150, name = "description")
    private String description;

    @Column( name = "units_in_stock")
    private Integer units_in_stock;
    
    @Column( name = "is_available")
    private Boolean is_available;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(
        name = "tb_product_category",
        joinColumns = {
            @JoinColumn( name = "product_id" )
        },
        inverseJoinColumns = {
            @JoinColumn( name = "category_id" )
        }
    )
    @JsonIgnore
    private Set<Category> categories;
    
    
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference 
    private Set<ProductPricing> pricings;
    

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<ProductImage> images;


    @ManyToOne
    @JoinColumn(name = "business_id")
    @JsonManagedReference
    private Business business;
    
    
}
