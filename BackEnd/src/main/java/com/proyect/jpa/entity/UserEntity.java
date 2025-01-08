package com.proyect.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * esta entidad contiene la segunda version para almacenar usuario y por separado tendra roles
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fist_name")
    private String fistName;

    private String country;

    private String email;

    private String password;

    private boolean enable;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity =RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();


    public void setFechaCreacion(Date date) {
        // Convertir Date a LocalDateTime
        this.fechaCreacion = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    public void setFechaActualizacion(Date date) {
        // Convertir Date a LocalDateTime
        this.fechaActualizacion = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
