package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.AuthResponse;
import com.ae.gestion_etudiants.DTo.FormLogin;
import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.reposetories.UtilisateurRepository;
import com.ae.gestion_etudiants.security.jwt.JwtConfig;
import com.ae.gestion_etudiants.security.jwt.JwtUtil;
import com.ae.gestion_etudiants.security.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    @Autowired
    public UtilisateurService(UtilisateurRepository repository, AuthenticationManager authenticationManager, ApplicationUserService applicationUserService, JwtUtil jwtUtil, JwtConfig jwtConfig) {
        this.utilisateurRepository = repository;
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
    }


    public void suprimerUtilisateur(Long id) {
        this.utilisateurRepository.deleteById(id);
    }

    public Utilisateur gUtilisateur(Long id) {
        return this.utilisateurRepository.findById(id).get();
    }

    public Utilisateur gUtilisateurByEmail(String email) {
        return this.utilisateurRepository.findByEmail(email).get();
    }

    public List<Utilisateur> gUtilisateurs() {
        return this.utilisateurRepository.findAll();
    }

    public AuthResponse login(FormLogin login) throws BadCredentialsException {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            final UserDetails userDetails = this.applicationUserService.loadUserByUsername(login.getEmail());
            final String jwt = this.jwtUtil.generateToken(userDetails);
            final String refreshToken = this.jwtUtil.generateRefreshToken(userDetails);
            return new AuthResponse(jwt, refreshToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password ", e);
        }
    }

    public AuthResponse refereshToken(String authorizationToken) throws Exception {
        try {
            if (authorizationToken != null && authorizationToken.startsWith(this.jwtConfig.getTokenPrefix())) {
                String jwt = authorizationToken.substring(this.jwtConfig.getTokenPrefix().length());
                String email = this.jwtUtil.extractEmail(jwt);
                final UserDetails userDetails = this.applicationUserService.loadUserByUsername(email);
                final String newJwt = this.jwtUtil.generateToken(userDetails);
                AuthResponse authResponse = new AuthResponse(newJwt, jwt);
                return authResponse;
            } else {
                throw new Exception("Vous ne pouvez pas acceder ");
            }
        } catch (Exception e) {
            throw new Exception("Vous ne pouvez pas acceder   ");
        }
    }

}

