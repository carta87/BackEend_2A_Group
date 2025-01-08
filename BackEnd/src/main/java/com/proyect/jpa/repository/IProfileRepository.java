package com.proyect.jpa.repository;

import com.proyect.jpa.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IProfileRepository extends CrudRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByName(String name);
}
