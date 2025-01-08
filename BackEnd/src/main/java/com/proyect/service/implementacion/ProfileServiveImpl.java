package com.proyect.service.implementacion;

import com.proyect.dto.ProfileDTO;
import com.proyect.jpa.entity.ProfileEntity;
import com.proyect.jpa.repository.IProfileRepository;
import com.proyect.mapper.ProfileMapper;
import com.proyect.service.ProfileServive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileServiveImpl implements ProfileServive {

    private final ProfileMapper profileMapper;
    private final IProfileRepository iProfileRepository;
    @Override
    public Set<ProfileDTO> findAll() {
        return profileMapper.mapToDto((List<ProfileEntity>) iProfileRepository.findAll());
    }

    @Override
    public ProfileDTO findById(Long id) {
        return profileMapper.mapToDto(iProfileRepository.findById(id).orElse(null));
    }

    @Override
    public ProfileDTO saveProfile(ProfileDTO profileDTO) {
        return profileMapper.mapToDto(iProfileRepository.save(profileMapper.mapToEntity(profileDTO)));
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) {
        if(iProfileRepository.findById(profileDTO.getId()).isPresent()){
            return profileMapper.mapToDto(iProfileRepository.save(profileMapper.mapToEntity(profileDTO)));
        } else {
            throw new RuntimeException("El perfil o permiso no existe");
        }
    }
}
