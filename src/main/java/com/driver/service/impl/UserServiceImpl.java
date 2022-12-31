package com.driver.service.impl;

import com.driver.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(user.getUserId());
        if(userEntity!=null){
            System.out.println("User entity already exists");
            return null;
        }
        String userId= UUID.randomUUID().toString();
        UserEntity userEntity2=UserEntity.builder().userId(userId).email(user.getEmail()).firstName(user.getFirstName())
                .lastName(user.getLastName()).build();
        userRepository.save(userEntity2);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null){
            System.out.println("User entity doesn't exist");
            return null;
        }
        UserDto userDto=UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName()).email(userEntity.getEmail()).build();
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null){
            System.out.println("User entity doesn't exist");
            return null;
        }
        UserDto userDto=UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName()).email(userEntity.getEmail()).build();
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null){
            System.out.println("User entity doesn't exist");
            return null;
        }
        UserDto userDto=user;
        userDto.setId(userEntity.getId());
        UserEntity user1= UserEntity.builder().id(userEntity.getId()).userId(user.getUserId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail()).build();
        userRepository.save(user1);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null) System.out.println("User entity doesn't exist");
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities= (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtos=new ArrayList<>();
        for(UserEntity userEntity:userEntities) userDtos.add(UserConverter.convertEntityToDto(userEntity));
        return userDtos;
    }
}