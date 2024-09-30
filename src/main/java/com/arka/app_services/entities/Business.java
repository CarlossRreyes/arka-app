package com.arka.app_services.entities;



import java.util.Set;
import java.util.UUID;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.*;

@Builder
@Data
@EqualsAndHashCode( exclude = { "categories", "products" } ,callSuper = false )
@ToString( exclude = "categories")
// @EqualsAndHashCode( exclude = "product")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_business")
public class Business extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "business_id" )
    private UUID business_id;    

    @Column( length = 10, name = "code")
    private String code;

    @Column( length = 50, name = "name")
    private String name;
    
    @Column( length = 150, name = "description")
    private String description;

    @Column( length = 200, name = "direction")
    private String direction;
    
    @Column( length = 200, name = "image_path")
    private String image_path;
    
    @Column( name = "is_active")
    private Boolean is_active;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference 
    private Set<Category> categories;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference 
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "business_type_id")
    @JsonManagedReference
    private BusinessType businessType;

}