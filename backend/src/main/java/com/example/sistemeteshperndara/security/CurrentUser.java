package com.example.sistemeteshperndara.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CurrentUser {

    private final JwtService jwtService;

    public CurrentUser(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String getCurrentRole() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        // Merr rolet nga token (nga authorities ose custom claim)
        Claims claims = jwtService.extractAllClaims(token);

        // Nëse rolet janë ruajtur si authorities në JWT (jo default), mund të lexosh kështu:
        Object roles = claims.get("authorities");
        if (roles instanceof String roleString) {
            return roleString.replace("ROLE_", ""); // p.sh. ADMIN
        }

        // Fallback
        return "UNKNOWN";
    }

    public String getCurrentUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getTenantIdFromToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        return jwtService.extractTenantId(token);
    }
}
