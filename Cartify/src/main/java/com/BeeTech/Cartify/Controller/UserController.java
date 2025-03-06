package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Dto.UserDto;
import com.BeeTech.Cartify.Exceptions.AlreadyExistsException;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Request.CreateUserRequest;
import com.BeeTech.Cartify.Request.UpdateUserRequest;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.User.UserServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceInt userServiceInt;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
        try {
            UserDto user = userServiceInt.getUserById(userId);
            return ResponseEntity.ok(new ApiResponse("User fetched successfully", user));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(CREATED)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateUserRequest request){

        try {
            UserDto user = userServiceInt.createUser(request);
            return  ResponseEntity
                    .status(CREATED)
                    .body(new ApiResponse("User Created Successfully", user));
        } catch (AlreadyExistsException e) {
            return ResponseEntity
                    .status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }

    }


    @PutMapping("/update/user/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId){
        try {
            UserDto user = userServiceInt.updateUser(request, userId);
            return ResponseEntity.ok(new ApiResponse("User successfully updated", user));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity
                   .status(NOT_FOUND)
                   .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        try {
            userServiceInt.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
