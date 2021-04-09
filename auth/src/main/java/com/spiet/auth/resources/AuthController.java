package com.spiet.auth.resources;

import com.spiet.auth.VOs.UserVO;
import com.spiet.auth.jwt.JwtTokenProvider;
import com.spiet.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository repo;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository repo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repo = repo;
    }

    @RequestMapping("/teste")
    public String teste() {
        return "ok";
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserVO userVO) {
        try {
            var username = userVO.getUsername();
            var password = userVO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repo.findByUsername(username);

            var token = "";

            if (user != null) {
                token = jwtTokenProvider.createToken(username, user.getRoles());
            }
            else {
                throw new UsernameNotFoundException("Usuario nao encontrado");
            }

            Map<Object, Object> model = new HashMap<>();

            model.put("username", username);
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Dados inválidos");
        }
    }


}
