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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
        http.headers().frameOptions().disable();// poretection vers les frames
        http.cors().and()
                .authorizeRequests().antMatchers(
                "/api/users/login",
                "/api/users/prof/signup",
                "/api/users/admin/signup",
                "/api/users/etudiants/signup",
                "/api/users/refreshToken").permitAll();// permet URL /signup
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// ACTIVER MODE AUTH STATELESS
        http.authorizeRequests().anyRequest().authenticated();// forcer authentification pour les autres url
        http.addFilterBefore(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class);// ajouter filtre jwt
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
      //  configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.addAllowedHeader("*");
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
