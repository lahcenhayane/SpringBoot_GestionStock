package com.project.app.Controllers;

import com.project.app.Entities.UserEntity;
import com.project.app.Requests.UserRequest;
import com.project.app.Responses.UserResponse;
import com.project.app.Services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> CreateNewUser(@RequestBody UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        UserEntity user = userService.createUser(userEntity);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }
}
