package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {


    @PostMapping("/login")
    public String login(@RequestBody LoginDTO body) {
        return "token";
    }


}
