package br.com.jmtech.infrastructure.access;

import br.com.jmtech.adapters.repository.UsuarioRepository;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityAccess {

    private static final String SECRET_KEY = "ChavesecRETAparapratariadebronzedoourodacasa@!#5765432paraexuberanciaDDttr";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean autenticar(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario != null){
            return passwordEncoder.matches(senha, usuario.getSenha());
        }

        return false;
    }

    public String gerarToken(String username) {
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
