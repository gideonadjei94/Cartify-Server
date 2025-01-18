package com.BeeTech.Cartify.Service.User;

import com.BeeTech.Cartify.Dto.UserDto;
import com.BeeTech.Cartify.Exceptions.AlreadyExistsException;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Mappers.UserMapper;
import com.BeeTech.Cartify.Model.User;
import com.BeeTech.Cartify.Repository.UserRepository;
import com.BeeTech.Cartify.Request.CreateUserRequest;
import com.BeeTech.Cartify.Request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInt {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                        .map(req -> {
                            User user = new User();
                            user.setEmail(request.getEmail());
                            user.setPassword(passwordEncoder.encode(request.getPassword()));
                            user.setFirstName(request.getFirstName());
                            user.setLastName(request.getLastName());
                            User savedUser = userRepository.save(user);
                            return userMapper.apply(savedUser);
                        }).orElseThrow(() -> new AlreadyExistsException(request.getEmail() + " already exists"));
    }

    @Override
    public UserDto updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            User savedUser = userRepository.save(user);
            return userMapper.apply(savedUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found");
                });
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
