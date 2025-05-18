package com.afser.security.service;

import com.afser.security.model.User;
import com.afser.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTservice  jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user) {
    Authentication authentication = authenticationManager.
            authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    if (authentication.isAuthenticated())
        return jwtService.generateToken();
    return "fail";
    }
}
