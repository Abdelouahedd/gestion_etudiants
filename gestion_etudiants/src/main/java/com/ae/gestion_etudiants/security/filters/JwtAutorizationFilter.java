package com.ae.gestion_etudiants.security.filters;

import com.ae.gestion_etudiants.security.jwt.JwtConfig;
import com.ae.gestion_etudiants.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtAutorizationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private JwtConfig jwtConfig;

    public JwtAutorizationFilter(JwtUtil jwtUtil, JwtConfig jwtConfig) {
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationToken = request.getHeader(this.jwtConfig.getAuthorizationHeader());

        if (authorizationToken != null && authorizationToken.startsWith(this.jwtConfig.getTokenPrefix())) {
            try {
                String jwt = authorizationToken.substring(this.jwtConfig.getTokenPrefix().length());
                String email = this.jwtUtil.extractEmail(jwt);
                Collection<GrantedAuthority> authorities = this.jwtUtil.extractRoles(jwt);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                Map<String, Object> idToken = new HashMap<>();
                idToken.put("message", "Vous pouvez pas acceder au  " + request.getRequestURL().toString());
                idToken.put("status", HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json ");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
