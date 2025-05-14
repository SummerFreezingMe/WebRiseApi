package com.example.webriseapi.mapper;

import com.example.webriseapi.model.User;
import com.example.webriseapi.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

}