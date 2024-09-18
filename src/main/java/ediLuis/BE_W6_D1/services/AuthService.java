package ediLuis.BE_W6_D1.services;


import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    public checkCredentialsAndGenerateToken(LoginDTO body){

    }
}
