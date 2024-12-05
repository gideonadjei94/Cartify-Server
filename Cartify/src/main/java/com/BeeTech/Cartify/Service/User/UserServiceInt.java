package com.BeeTech.Cartify.Service.User;

import com.BeeTech.Cartify.Dto.UserDto;
import com.BeeTech.Cartify.Model.User;
import com.BeeTech.Cartify.Request.CreateUserRequest;
import com.BeeTech.Cartify.Request.UpdateUserRequest;

public interface UserServiceInt {

    UserDto getUserById(Long userId);
    UserDto createUser(CreateUserRequest request);
    UserDto updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

}
