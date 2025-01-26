package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Request.LoginRequest;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Response.JwtResponse;
import com.BeeTech.Cartify.Security.JWT.JwtUtils;
import com.BeeTech.Cartify.Security.User.ShopUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        try {
            System.out.println(loginRequest);
            Authentication authUser = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authUser);
            String token = jwtUtils.generateTokenForUser(authUser);
            ShopUserDetails userDetails = (ShopUserDetails) authUser.getPrincipal();
            JwtResponse jwtResponse = new  JwtResponse(userDetails.getId(), token);
            return ResponseEntity.ok(new ApiResponse("User Login Successful", jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
