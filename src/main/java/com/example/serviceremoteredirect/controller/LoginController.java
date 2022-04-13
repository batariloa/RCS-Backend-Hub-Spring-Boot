package com.example.serviceremoteredirect.controller;


import com.example.serviceremoteredirect.jwt.JWTUtility;
import com.example.serviceremoteredirect.model.JwtRequest;
import com.example.serviceremoteredirect.model.JwtResponse;
import com.example.serviceremoteredirect.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e){
            System.out.println("Haven't provided correct credentials");
            throw new Exception("Invalid credentials");

        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtility.generateToken(userDetails);



        return new JwtResponse(token, "94.189.234.3");
    }
}