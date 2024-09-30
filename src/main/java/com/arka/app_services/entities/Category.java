package com.arka.app_services.entities;

import java.util.UUID;

import com.arka.app_services.constants.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( name = "category_id" )
    private UUID category_id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    @JsonManagedReference
    private Business business;
    
    @Column( length = 50, name = "code")
    private String code;

    @Column( length = 50, name = "name")
    private String name;

    @Column( length = 150, name = "description")
    private String description;
    
    @Column( length = 200, name = "image_path")
    private String image_path;
    
    @Column( name = "is_active")
    private Boolean is_active;


    
}
