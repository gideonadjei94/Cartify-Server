package com.BeeTech.Cartify.Mappers;

import com.BeeTech.Cartify.Dto.*;
import com.BeeTech.Cartify.Model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
