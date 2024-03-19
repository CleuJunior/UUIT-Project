package com.interion.uuit.entities;

import com.interion.uuit.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import static com.interion.uuit.enums.Role.USER;
import static java.util.Collections.singleton;

@Document("User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class User<T extends Serializable> extends BaseMongoEntity implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @DBRef
    private T dbEntity;
    private String username;
    private String password;
    private Role role;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(ObjectId id, T dbEntity, String username, String password, Role role) {
        super(id);
        this.dbEntity = dbEntity;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(T dbEntity, String username, String password, Role role) {
        this.dbEntity = dbEntity;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singleton(new SimpleGrantedAuthority(USER.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var user = (User<?>) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
