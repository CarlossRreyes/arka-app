package com.arka.app_services.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.arka.app_services.constants.Auditable;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_role")
public class Role extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( unique = true, nullable = false, name = "role_id" )
    private UUID role_id;

    @Column( name = "name", length = 60 )
    private String name;

    @Column( name = "description", length = 80 )
    private String description;

    @Column(name = "state")
    private Boolean state;

    // @ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(
        name = "tb_role_privilege",
        joinColumns = {
            @JoinColumn( name = "role_id" )
        },
        inverseJoinColumns = {
            @JoinColumn( name = "privilege_id" )
        }
    )
    private Set<Privilege> privileges;
    
}
