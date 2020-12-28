package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.FormLogin;
import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.reposetories.UtilisateurRepository;
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

    @Autowired
    public UtilisateurService(UtilisateurRepository repository, AuthenticationManager authenticationManager, ApplicationUserService applicationUserService, JwtUtil jwtUtil) {
        this.utilisateurRepository = repository;
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.jwtUtil = jwtUtil;
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

    public String login(FormLogin login) throws BadCredentialsException {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            final UserDetails userDetails = this.applicationUserService.loadUserByUsername(login.getEmail());
            final String jwt = this.jwtUtil.generateToken(userDetails);
            return jwt;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password ", e);
        }
    }

}

