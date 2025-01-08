package com.proyect.service.implementacion;

import com.proyect.dto.AuthResponse;
import com.proyect.dto.LoginRequest;
import com.proyect.dto.RegisterRequest;
import com.proyect.jpa.entity.ProfileEntity;
import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.entity.UserEntity;
import com.proyect.jpa.repository.IProfileRepository;
import com.proyect.jpa.repository.IRoleRepository;
import com.proyect.jpa.repository.IUserRepository;
import com.proyect.jwt.JwtUtil;
import com.proyect.service.IAuthService;
import com.proyect.util.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final JwtUtil jwtUtil;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;
    private final IProfileRepository iProfileRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserEntity userEntity= iUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        AtomicReference<String> rolUsuario = new AtomicReference<>("");
        userEntity.getRoles().forEach(roleEntity -> rolUsuario.set(roleEntity.getName()));
        String token = jwtUtil.getToken(userEntity);

        return AuthResponse.builder()
                .rol(rolUsuario.toString())
                .username(loginRequest.getUsername())
                .status(Boolean.TRUE)
                .token(token)
                .message("Token creado corectamente")
                .build();
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        // Buscar o crear el permiso "READ" por defecto
        ProfileEntity readPermission = iProfileRepository.findByName(Constantes.READ)
                .orElseGet(() -> iProfileRepository.save(ProfileEntity.builder()
                        .enabled(Boolean.TRUE)
                        .name(Constantes.READ).build()));

        // Buscar o crear el rol
        RoleEntity userRole = iRoleRepository.findByName(Constantes.USER)
                .orElseGet(() -> {
                    RoleEntity newRole = RoleEntity.builder()
                            .name(Constantes.USER)
                            .profileList(new HashSet<>(Set.of(readPermission)))
                            .build();
                    return iRoleRepository.save(newRole);
                });

        UserEntity userEntity = UserEntity.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fistName(registerRequest.getFirsName())
                .lastName(registerRequest.getLastName())
                .country(registerRequest.getCountry())
                .email(registerRequest.getEmail())
                .enable(Boolean.TRUE)
                .accountNoExpired(Boolean.TRUE)
                .credentialNoExpired(Boolean.TRUE)
                .accountNoLocked(Boolean.TRUE)
                .roles(Set.of(userRole))// Asociar el rol creado
                .build();
        userEntity.setFechaCreacion(new Date());
        iUserRepository.save(userEntity);

        return AuthResponse.builder()
                .username(registerRequest.getUsername())
                .token(jwtUtil.getToken(userEntity))
                .message("Usuario creado corectamente")
                .status(Boolean.TRUE)
                .build();
    }
}
