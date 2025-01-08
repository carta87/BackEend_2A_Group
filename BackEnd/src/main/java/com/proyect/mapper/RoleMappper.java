package com.proyect.mapper;

import com.proyect.dto.RoleDTO;
import com.proyect.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMappper {

    Set<RoleDTO> mapToDto(List<RoleEntity> roleEntities);

    RoleDTO mapToDto(RoleEntity roleEntity);

    RoleEntity mapToEntity(RoleDTO roleDTO);
}
