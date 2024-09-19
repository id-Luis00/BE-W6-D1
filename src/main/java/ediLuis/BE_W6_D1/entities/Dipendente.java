package ediLuis.BE_W6_D1.entities;

import ediLuis.BE_W6_D1.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Dipendente implements UserDetails {


    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public Dipendente(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = Roles.USER;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roles.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}
