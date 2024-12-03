package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Service.User.UserServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceInt userServiceInt;
}
