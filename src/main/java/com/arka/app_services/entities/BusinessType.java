package com.arka.app_services.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode( exclude = "businesses" ,callSuper = false )
@ToString( exclude = "businesses" )
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_business_type")
public class BusinessType extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "business_type_id" )
    private UUID business_type_id;
    
    @Column( length = 50, name = "code")
    private String code;

    @Column( length = 50, name = "name")
    private String name;

    @Column( length = 150, name = "description")
    private String description;

    @Column( name = "is_active")
    private Boolean is_active;

    @OneToMany(mappedBy = "businessType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference 
    private Set<Business> businesses;



}