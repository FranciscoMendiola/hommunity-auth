package com.syrion.hommunity_api.api.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.config.jwt.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")  
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String correo = loginData.get("correo");
        String contraseña = loginData.get("contraseña");

        Optional<Usuario> optUsuario = usuarioRepository.findByCorreo(correo);

        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }

        Usuario usuario = optUsuario.get();

        if (!passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }

            String token = jwtUtil.generateToken(usuario);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", Map.of(
                        "id", usuario.getIdUsuario(),
                        "nombre", usuario.getNombre(),
                        "correo", usuario.getCorreo(),
                        "estado", usuario.getEstado()
                )
        ));
    }
}
