package br.com.jmtech.adapters.controller;


import br.com.jmtech.application.dto.Auth.LoginRequest;
import br.com.jmtech.infrastructure.access.SecurityAccess;
import br.com.jmtech.infrastructure.access.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação de usuários")
public class AuthController {

    @Autowired
    private SecurityAccess securityAccess;

    @Operation(summary = "Realiza login do usuário")
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
