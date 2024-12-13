package com.proyect.service.implementacion;

import com.proyect.dto.UserDTO;
import com.proyect.jpa.entity.UserEntity;
import com.proyect.jpa.repository.IUserRepository;
import com.proyect.mapper.UserMapper;
import com.proyect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final IUserRepository iUserRepository;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.mapToDto((List<UserEntity>) iUserRepository.findAll()) ;
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.maoToDto(iUserRepository.findById(id).orElse(new UserEntity()));
    }

    @Override
    public void saveUser(UserDTO user) {
        iUserRepository.save(userMapper.mapToEntity(user));
    }

    @Override
    public void updateUser(UserDTO user) {
        iUserRepository.save(userMapper.mapToEntity(user));
    }

    @Override
    public void deleteUser(Long id) {
        iUserRepository.deleteById(id);
    }
}
