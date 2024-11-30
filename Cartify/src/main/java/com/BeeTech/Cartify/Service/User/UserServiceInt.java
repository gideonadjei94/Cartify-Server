package com.BeeTech.Cartify.Service.User;

import com.BeeTech.Cartify.Model.User;
import com.BeeTech.Cartify.Request.CreateUserRequest;
import com.BeeTech.Cartify.Request.UpdateUserRequest;

public interface UserServiceInt {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

}
