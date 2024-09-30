package com.arka.app_services.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arka.app_services.constants.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User extends Auditable implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column( unique = true, nullable = false, name = "user_id" )
    private UUID user_id;

    @Column( name = "email", length = 150, unique =  true)
    private String email;

    @Column( name = "password", length = 150 )
    private String password;

    // @ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(
        name = "tb_user_role",
        joinColumns = {
            @JoinColumn( name = "user_id" )
        },
        inverseJoinColumns = {
            @JoinColumn( name = "role_id" )
        }
    )
    private Set<Role> roles;

    @Column(name = "state")
    private Boolean state;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {      
        return getGrantedAuthorities( getPrivileges( getRoles() ) );
    }

    private Set<String> getPrivileges (Set<Role> roles ){
        Set<String> privileges = new HashSet<>();
        Set<Privilege> collections = new HashSet<>();
        roles.forEach( role -> {
            privileges.add( "ROLE_" + role.getName() );
            collections.addAll( role.getPrivileges() );
        });
        
        collections.forEach( collection -> {
            privileges.add( collection.getName() );
        });        

        return privileges;
    }


    private Set<GrantedAuthority> getGrantedAuthorities( Set<String> privileges ){
        Set<GrantedAuthority> authorities = new HashSet<>();
        privileges.forEach( privilege -> {
            authorities.add( new SimpleGrantedAuthority( privilege ));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return state;
    }


}