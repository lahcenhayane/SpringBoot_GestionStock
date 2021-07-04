package com.project.app.Services;

import com.project.app.Entities.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    UserEntity getUser(long id);
    List<UserEntity> getAllUsers(int p, int l);
    UserEntity createUser(UserEntity userEntity);
    UserEntity editUser(long id, UserEntity user);
    void deleteUser(long id);
}
