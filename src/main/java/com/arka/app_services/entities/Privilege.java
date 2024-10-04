package com.arka.app_services.entities;


import java.util.UUID;
import com.arka.app_services.constants.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_privilege")
public class Privilege extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( unique = true, nullable = false, name = "privilege_id" )
    private UUID privilege_id;

    @Column( name = "name", length = 60 )
    private String name;

    @Column( name = "description", length = 80 )
    private String description;

    @Column(name = "state")
    private Boolean state;

    // @ManyToMany( mappedBy = "privileges", cascade = CascadeType.ALL)
    // @OneToMany( mappedBy = "privileges", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Role> roles;


}
