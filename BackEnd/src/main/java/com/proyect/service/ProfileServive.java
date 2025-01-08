package com.proyect.service;

import com.proyect.dto.ProfileDTO;
import java.util.Set;

public interface ProfileServive {

    Set<ProfileDTO> findAll();

    ProfileDTO findById(Long id);

    ProfileDTO saveProfile(ProfileDTO profileDTO);

    ProfileDTO updateProfile(ProfileDTO profileDTO);
}

