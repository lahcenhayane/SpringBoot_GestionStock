package com.project.app.Controllers;

import com.project.app.Entities.UserEntity;
import com.project.app.Requests.UserRequest;
import com.project.app.Responses.UserResponse;
import com.project.app.Services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> CreateNewUser(@RequestBody UserRequest userRequest){
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        UserEntity user = userService.createUser(userEntity);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") long id){
        UserEntity userEntity = userService.getUser(id);
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int p,
                                                          @RequestParam(value = "limit", defaultValue = "5") int l){
        List<UserResponse> userResponses = new ArrayList<>();
        List<UserEntity> userEntity = userService.getAllUsers(p,l);
        for (UserEntity row : userEntity) {
            UserResponse userResponse = modelMapper.map(row, UserResponse.class);
            userResponses.add(userResponse);
        }
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable("id") long id, @RequestBody UserRequest userRequest){
        UserEntity user = modelMapper.map(userRequest, UserEntity.class);
        UserEntity userEntity = userService.editUser(id, user);
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }
}
