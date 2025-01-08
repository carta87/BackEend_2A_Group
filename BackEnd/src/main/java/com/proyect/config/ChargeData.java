package com.proyect.config;

import com.proyect.jpa.entity.ProfileEntity;
import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.entity.UserEntity;
import com.proyect.jpa.repository.IUserRepository;
import com.proyect.util.Constantes;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ChargeData {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void cargarDatosIniciales() {
        List<UserEntity> entityList= (List<UserEntity>) userRepository.findAll();
        if(entityList.isEmpty()){
            /* Create PERMISSIONS */
            ProfileEntity createPermission = ProfileEntity.builder()
                    .name(Constantes.CREATE)
                    .enabled(Boolean.TRUE)
                    .build();

            ProfileEntity readPermission = ProfileEntity.builder()
                    .name(Constantes.READ)
                    .enabled(Boolean.TRUE)
                    .build();

            ProfileEntity updatePermission = ProfileEntity.builder()
                    .name(Constantes.UPDATE)
                    .enabled(Boolean.TRUE)
                    .build();

            ProfileEntity deletePermission = ProfileEntity.builder()
                    .name(Constantes.DELETE)
                    .enabled(Boolean.TRUE)
                    .build();

            /* Create ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .name(Constantes.ADMIN)
                    .enabled(Boolean.TRUE)
                    .profileList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .name(Constantes.USER)
                    .enabled(Boolean.TRUE)
                    .profileList(Set.of(createPermission, readPermission))
                    .build();

            /* CREATE USERS */
            UserEntity admin = UserEntity.builder()
                    .username("admin")
                    .email("admin@hotmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .enable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity user = UserEntity.builder()
                    .username("user")
                    .email("user@hotmail.com")
                    .password(passwordEncoder.encode("user"))
                    .enable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleUser))
                    .build();

            UserEntity userCarlos = UserEntity.builder()
                    .username("carlos")
                    .email("carlos@hotmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .enable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleAdmin))
                    .build();
            userRepository.saveAll(List.of(admin, user, userCarlos));
        }
    }
}
