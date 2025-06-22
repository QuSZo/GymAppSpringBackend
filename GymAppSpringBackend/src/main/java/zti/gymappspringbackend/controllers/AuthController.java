package zti.gymappspringbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.auth.JwtUtil;
import zti.gymappspringbackend.dtos.user.AuthenticationRequest;
import zti.gymappspringbackend.dtos.user.JwtDto;
import zti.gymappspringbackend.dtos.user.RegisterDto;
import zti.gymappspringbackend.entities.User;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.UserRepository;
import zti.gymappspringbackend.services.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto request) {
        User userExisted = userRepository.findByEmail(request.getEmail());
        if(userExisted != null)
            throw new BadRequestGymAppException("The email address already exists");

        String hashPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), hashPassword, "user");
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public JwtDto loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch(Exception exception){
            throw new BadRequestGymAppException("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails, userRepository.findByEmail(authenticationRequest.getEmail()).getId());

        JwtDto jwtDto = new JwtDto();
        jwtDto.setAccessToken(jwt);
        return jwtDto;
    }
}
