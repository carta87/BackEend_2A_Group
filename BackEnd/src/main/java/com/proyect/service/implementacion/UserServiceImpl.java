package com.proyect.service.implementacion;

import com.proyect.dto.UserDTO;
import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.entity.UserEntity;
import com.proyect.jpa.repository.IRoleRepository;
import com.proyect.jpa.repository.IUserRepository;
import com.proyect.mapper.UserMapper;
import com.proyect.service.UserService;
import com.proyect.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final IRoleRepository iRoleRepository;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.mapToDto((List<UserEntity>) iUserRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.maoToDto(iUserRepository.findById(id).orElse(new UserEntity()));
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        // Verificar si el nombre de usuario ya está registrado
        Optional<UserEntity> existingUser = iUserRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("el username se encuentra registrado.");
        }

        // Mapear el DTO a la entidad UserEntity
        UserEntity userEntity = userMapper.mapToEntity(userDTO);

        Set<RoleEntity> roles = new HashSet<>();
        for (RoleEntity role : userDTO.getRoles()) {
            Optional<RoleEntity> existingRole = iRoleRepository.findByName(role.getName());
            existingRole.ifPresent(roles::add);
        }
        // Asignar los roles gestionados al usuario
        userEntity.setRoles(roles);
        userEntity.setFechaCreacion(new Date());
        userEntity.setEnable(Boolean.TRUE);
        userEntity.setAccountNoLocked(Boolean.TRUE);
        userEntity.setAccountNoExpired(Boolean.TRUE);
        userEntity.setCredentialNoExpired(Boolean.TRUE);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Guardar el usuario, los roles y perfil
        iUserRepository.save(userEntity);
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        // Mapear el DTO a la entidad UserEntity
        UserEntity userEntity = userMapper.mapToEntity(userDTO);

        Set<RoleEntity> roles = new HashSet<>();
        for (RoleEntity role : userDTO.getRoles()) {
            Optional<RoleEntity> existingRole = iRoleRepository.findByName(role.getName());
            existingRole.ifPresent(roles::add);
        }

        // Asignar los roles gestionados al usuario
        userEntity.setRoles(roles);
        userEntity.setFechaActualizacion(new Date());
        //userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity user = iUserRepository.findById(userDTO.getId()).orElse(null);
        if (user != null && user.getFechaCreacion() != null) {
            userEntity.setFechaCreacion(DateUtil.localDateTimeToDate(user.getFechaCreacion()));
        }

        // Guardar el usuario, los roles y perfil
        iUserRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        iUserRepository.deleteById(id);
    }
}
