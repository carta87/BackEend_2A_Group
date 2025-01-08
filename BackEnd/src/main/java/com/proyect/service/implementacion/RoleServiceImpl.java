package com.proyect.service.implementacion;

import com.proyect.dto.RoleDTO;
import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.repository.IRoleRepository;
import com.proyect.mapper.RoleMappper;
import com.proyect.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMappper roleMappper;
    private  final IRoleRepository iRoleRepository;

    @Override
    public Set<RoleDTO> findAll() {
        return roleMappper.mapToDto((List<RoleEntity>) iRoleRepository.findAll());
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMappper.mapToDto(iRoleRepository.findById(id).orElse(new RoleEntity()));
    }

    @Override
    public RoleDTO saveRol(RoleDTO roleDTO) {
        return roleMappper.mapToDto(iRoleRepository.save(roleMappper.mapToEntity(roleDTO)));
    }

    @Override
    public RoleDTO updateRol(RoleDTO roleDTO) {
        if(iRoleRepository.findById(roleDTO.getId()).isPresent()){
            return roleMappper.mapToDto(iRoleRepository.save(roleMappper.mapToEntity(roleDTO)));
        } else {
            throw new RuntimeException("El rol no existe");
        }
    }

    @Override
    public boolean findByName(String name) {
        return iRoleRepository.findByName(name).isPresent();
    }
}
