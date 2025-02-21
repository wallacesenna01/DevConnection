package com.br.wallaceartur.DevConnection.services;

import com.br.wallaceartur.DevConnection.dtos.AuthenticationDto;
import com.br.wallaceartur.DevConnection.dtos.LoginResponseDto;
import com.br.wallaceartur.DevConnection.dtos.RegisterDto;
import com.br.wallaceartur.DevConnection.model.User;
import com.br.wallaceartur.DevConnection.model.UserProfile;
import com.br.wallaceartur.DevConnection.repositories.UserProfileRepository;
import com.br.wallaceartur.DevConnection.repositories.UserRepository;
import com.br.wallaceartur.DevConnection.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.context.ApplicationContext;


@Service
public class AuthorizationService implements UserDetailsService {


    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserProfileRepository userProfileRepository;


   private AuthenticationManager authenticationManagerBean;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }


    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data ) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByEmail(registerDto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.email(), encryptedPassword, registerDto.role());
        this.userRepository.save(newUser);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(newUser);
        userProfile.setName("Novo Usuario");
        userProfile.setBios("");
        userProfile.setProfilePictureUrl(null);
        userProfile.setCoverPictureUrl(null);

        userProfileRepository.save(userProfile);


        var token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
