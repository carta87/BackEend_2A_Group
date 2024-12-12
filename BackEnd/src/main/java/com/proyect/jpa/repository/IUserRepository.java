package com.proyect.jpa.repository;

import com.proyect.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long > {
   Optional<UserEntity> findByUsername(String username);
}
