package com.proyect;

import com.proyect.jpa.entity.ProfileEntity;
import com.proyect.jpa.entity.RoleEntity;
import com.proyect.jpa.entity.RoleEnum;
import com.proyect.jpa.entity.UserEntity;
import com.proyect.jpa.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {

			List<UserEntity> entityList= (List<UserEntity>) userRepository.findAll();
			if(entityList.isEmpty()){
				/* Create PERMISSIONS */
				ProfileEntity createPermission = ProfileEntity.builder()
						.name("CREATE")
						.build();

				ProfileEntity readPermission = ProfileEntity.builder()
						.name("READ")
						.build();

				ProfileEntity updatePermission = ProfileEntity.builder()
						.name("UPDATE")
						.build();

				ProfileEntity deletePermission = ProfileEntity.builder()
						.name("DELETE")
						.build();

				/* Create ROLES */
				RoleEntity roleAdmin = RoleEntity.builder()
						.roleEnum(RoleEnum.ADMIN)
						.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
						.build();

				RoleEntity roleUser = RoleEntity.builder()
						.roleEnum(RoleEnum.USER)
						.permissionList(Set.of(createPermission, readPermission))
						.build();

				/* CREATE USERS */
				UserEntity admin = UserEntity.builder()
						.username("admin")
						.email("admin@hotmail.com")
						.password(passwordEncoder.encode("admin"))
						.isEnable(Boolean.TRUE)
						.accountNoExpired(Boolean.TRUE)
						.accountNoLocked(Boolean.TRUE)
						.credentialNoExpired(Boolean.TRUE)
						.roles(Set.of(roleAdmin))
						.build();

				UserEntity user = UserEntity.builder()
						.username("user")
						.email("user@hotmail.com")
						.password(passwordEncoder.encode("user"))
						.isEnable(Boolean.TRUE)
						.accountNoExpired(Boolean.TRUE)
						.accountNoLocked(Boolean.TRUE)
						.credentialNoExpired(Boolean.TRUE)
						.roles(Set.of(roleUser))
						.build();

				UserEntity userCarlos = UserEntity.builder()
						.username("carlos")
						.email("carlos@hotmail.com")
						.password(passwordEncoder.encode("1234"))
						.isEnable(Boolean.TRUE)
						.accountNoExpired(Boolean.TRUE)
						.accountNoLocked(Boolean.TRUE)
						.credentialNoExpired(Boolean.TRUE)
						.roles(Set.of(roleAdmin))
						.build();
				userRepository.saveAll(List.of(admin, user, userCarlos));
			}
		};
	}
}
