package com.proyect.jpa.repository;

import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
