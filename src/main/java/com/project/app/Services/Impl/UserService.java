package com.project.app.Services.Impl;

import com.project.app.Entities.UserEntity;
import com.project.app.Repositories.UserRepository;
import com.project.app.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException("This Email Not Found "+email);
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
