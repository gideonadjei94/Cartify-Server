package com.BeeTech.Cartify.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController {

    @RequestMapping("/")
    public String greet(){
        return "Hello, Welcome to BeeTech";
    }

}
