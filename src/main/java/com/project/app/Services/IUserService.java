package com.project.app.Services;

import com.project.app.Entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    void deleteUser(long id);
}
