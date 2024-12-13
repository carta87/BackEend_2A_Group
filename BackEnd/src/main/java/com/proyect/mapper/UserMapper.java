package com.proyect.mapper;

import com.proyect.dto.UserDTO;
import com.proyect.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTO> mapToDto(List<UserEntity> userEntities);

    UserDTO maoToDto(UserEntity userEntity);

    UserEntity mapToEntity(UserDTO userDTO);
}
