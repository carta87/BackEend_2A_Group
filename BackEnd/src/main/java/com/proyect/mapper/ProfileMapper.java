package com.proyect.mapper;

import com.proyect.dto.ProfileDTO;
import com.proyect.jpa.entity.ProfileEntity;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Set<ProfileDTO> mapToDto(List<ProfileEntity> profileEntities);

    ProfileDTO mapToDto(ProfileEntity profileEntity);

    ProfileEntity mapToEntity(ProfileDTO profileDTO);
}
