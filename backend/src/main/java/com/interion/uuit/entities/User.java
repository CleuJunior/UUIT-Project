package com.interion.uuit.entities;

import com.interion.uuit.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Transient;
import java.util.Collection;

import static com.interion.uuit.enums.Role.ADMIN;
import static com.interion.uuit.enums.Role.USER;
import static java.util.Collections.singleton;
import static java.util.List.of;

@Document("User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class User extends BaseMongoEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public User(ObjectId id, String firstName, String lastName, String email, String password, Role role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == ADMIN) {
            return of(new SimpleGrantedAuthority(ADMIN.getName()),
                    new SimpleGrantedAuthority(USER.name()));
        }
        else return singleton(new SimpleGrantedAuthority(USER.name()));
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
        return true;
    }
}
