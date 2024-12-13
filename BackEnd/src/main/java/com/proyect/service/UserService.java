package com.proyect.service;

import com.proyect.dto.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    void saveUser(UserDTO user);

    void updateUser(UserDTO user);

    void deleteUser(Long id);


}
