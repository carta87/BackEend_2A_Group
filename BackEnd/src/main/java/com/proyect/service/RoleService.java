package com.proyect.service;

import com.proyect.dto.RoleDTO;
import java.util.Set;

public interface RoleService {

    Set<RoleDTO> findAll();

    RoleDTO findById(Long id);

    RoleDTO saveRol(RoleDTO roleDTO);

    RoleDTO updateRol(RoleDTO roleDTO);
    boolean findByName(String name);
}
