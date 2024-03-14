package com.interion.uuit.entities;

import com.interion.uuit.enums.Role;
import com.interion.uuit.enums.Role2;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public abstract class BaseEntityDetails implements UserDetails {

    @Id
    protected ObjectId id;
    protected String email;
    protected String registration;
    protected String password;
    protected Set<Role2> roles = new HashSet<>();
    @CreatedDate
    protected LocalDateTime creationDate;
    @LastModifiedDate
    protected LocalDateTime updateDate;

    protected BaseEntityDetails() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    protected BaseEntityDetails(String email, String registration, String password) {
        this.email = email;
        this.registration = registration;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    protected BaseEntityDetails(ObjectId id, String email, String registration, String password) {
        this.id = id;
        this.email = email;
        this.registration = registration;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return true;
    }


}
