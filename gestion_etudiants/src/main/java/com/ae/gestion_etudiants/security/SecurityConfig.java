package com.ae.gestion_etudiants.security;

import com.ae.gestion_etudiants.security.filters.JwtAutorizationFilter;
import com.ae.gestion_etudiants.security.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ApplicationUserService userDetailsService;
    private final JwtAutorizationFilter jwtAutorizationFilter;

    @Autowired
    public SecurityConfig(JwtAutorizationFilter jwtAutorizationFilter, ApplicationUserService userDetailsService) {
        this.jwtAutorizationFilter = jwtAutorizationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// disactiver security statfull (SESSION ID)
        http.cors().disable();
        http.headers().frameOptions().disable();// poretection vers les frames
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// ACTIVER MODE AUTH STATELESS
        http.authorizeRequests().antMatchers("/api/users/login", "/api/users/prof/signup", "/api/users/admin/signup",
                "/api/users/etudiants/signup").permitAll();// permet URL /signup
        http.authorizeRequests().anyRequest().authenticated();// forcer authentification pour les autres url
        http.addFilterBefore(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class);// ajouter filtre jwt
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
