package com.BeeTech.Cartify.Service.User;

import com.BeeTech.Cartify.Exceptions.AlreadyExistsException;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.User;
import com.BeeTech.Cartify.Repository.UserRepository;
import com.BeeTech.Cartify.Request.CreateUserRequest;
import com.BeeTech.Cartify.Request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInt {
    private final UserRepository userRepository;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                        .map(req -> {
                            User user = new User();
                            user.setEmail(request.getEmail());
                            user.setPassword(request.getPassword());
                            user.setFirstName(request.getFirstName());
                            user.setLastName(request.getLastName());
                            return userRepository.save(user);
                        }).orElseThrow(() -> new AlreadyExistsException(request.getEmail() + " already exists"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found");
                });
    }
}
