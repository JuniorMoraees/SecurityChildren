package br.com.jmtech.adapters.controller;


import br.com.jmtech.application.dto.Auth.LoginRequest;
import br.com.jmtech.infrastructure.access.SecurityAccess;
import br.com.jmtech.infrastructure.access.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private SecurityAccess securityAccess = new SecurityAccess();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        if (securityAccess.autenticar(login.getUsername(), login.getPassword())) {
            String token = securityAccess.gerarToken(login.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }
}
