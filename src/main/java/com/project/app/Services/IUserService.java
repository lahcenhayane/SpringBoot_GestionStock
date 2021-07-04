package com.project.app.Services;

import com.project.app.Entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    UserEntity createUser(UserEntity userEntity);
}
