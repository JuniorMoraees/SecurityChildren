package br.com.jmtech.adapters.controller;

import br.com.jmtech.adapters.repository.UsuarioRepository;
import br.com.jmtech.application.dto.Auth.LoginRequest;
import br.com.jmtech.application.dto.Auth.RefreshRequest;
import br.com.jmtech.infrastructure.access.SecurityAccess;
import br.com.jmtech.infrastructure.access.TokenResponse;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação de usuários")
public class AuthController {

    @Autowired
    private SecurityAccess securityAccess;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Realiza login do usuário")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        if (securityAccess.autenticar(login.getUsername(), login.getPassword())) {
            Usuario usuario = usuarioRepository.findByUsername(login.getUsername());

            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuário não encontrado.");
            }

            if (!usuario.getAtivo()){
                return ResponseEntity.status(403).body("Usuario Inativo.");
            }

            String accessToken = securityAccess.gerarToken(usuario.getUsername(), usuario.getNome());
            String refreshToken = securityAccess.gerarRefreshToken(usuario.getUsername());

            return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        String username = securityAccess.validarRefreshToken(request.getRefreshToken());
        if (username != null) {
            Usuario usuario = usuarioRepository.findByUsername(username);
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuário não encontrado.");
            }
            String novoAccessToken = securityAccess.gerarToken(username, usuario.getNome());
            return ResponseEntity.ok(new TokenResponse(novoAccessToken, request.getRefreshToken()));
        } else {
            return ResponseEntity.status(401).body("Refresh token inválido ou expirado.");
        }
    }
}
