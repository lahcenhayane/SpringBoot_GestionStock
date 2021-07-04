package com.project.app.Services.Impl;

import com.project.app.Entities.UserEntity;
import com.project.app.Repositories.UserRepository;
import com.project.app.Requests.UserRequest;
import com.project.app.Services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException("This Email Not Found "+email);
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());
        if (user != null) throw new RuntimeException("This Email Already Exist "+user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers(int p, int l) {
        p--;
        if (p < 0) p = 0;
        ModelMapper modelMapper = new ModelMapper();
        List<UserEntity> users = new ArrayList<>();
        Page<UserEntity> page = userRepository.findAll(PageRequest.of(p,l));
        for (UserEntity row: page.getContent()) {
            users.add(modelMapper.map(row, UserEntity.class));
        }
        return users;
    }

    @Override
    public UserEntity getUser(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        if (userEntity == null) throw new RuntimeException("This User Not Found.");
        return userEntity;
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = getUser(id);
        if (userEntity == null)throw new RuntimeException("This User Not Found.");
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity editUser(long id, UserEntity user) {
        UserEntity userEntity = getUser(id);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setDateNaissance(user.getDateNaissance());
        return userRepository.save(userEntity);
    }
}
