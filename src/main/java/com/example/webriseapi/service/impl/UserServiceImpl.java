package com.example.webriseapi.service.impl;

import com.example.webriseapi.mapper.UserMapper;
import com.example.webriseapi.model.User;
import com.example.webriseapi.model.dto.UserDTO;
import com.example.webriseapi.repository.UserRepository;
import com.example.webriseapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Add User");
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUser(Long id) {
        logger.info(MessageFormat.format("Get User with id: {0}", id));
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.info(MessageFormat.format("Update User with id: {0}", id));
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info(MessageFormat.format("Delete User with id: {0}", id));
        userRepository.deleteById(id);
    }
}