package com.proyect.dto;

import com.proyect.jpa.entity.RoleEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String lastName;
    private String fistName;
    private String country;
    @Email
    private String email;
    private String password;
    private boolean enable;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;
    private Set<RoleEntity> roles = new HashSet<>();
}
