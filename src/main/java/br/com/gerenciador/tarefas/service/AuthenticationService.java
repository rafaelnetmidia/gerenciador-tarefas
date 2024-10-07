package br.com.gerenciador.tarefas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String JWT_KEY = "signinkey";
    private static final String AUTHORITIES = "authorities";
    private static final String BEARER = "Bearer ";
    private static final int EXPIRATION_TOKEN_ONE_HOUR = 3600000;


    static public void addJWTToken(HttpServletResponse response, Authentication authentication) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITIES, authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        String jwtToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN_ONE_HOUR))
                .signWith(SignatureAlgorithm.HS512, JWT_KEY)
                .addClaims(claims)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, BEARER+jwtToken);
        response.addHeader("Access-Control-Expose-Headers", HEADER_AUTHORIZATION);

    }

    static public Authentication getAuthentication(HttpServletRequest request) {

            String token = request.getHeader(HEADER_AUTHORIZATION);

            if (token != null) {
                Claims user = Jwts.parser()
                        .setSigningKey(JWT_KEY)
                        .parseClaimsJws(token.replace(BEARER, ""))
                        .getBody();

                if (user != null) {

                    Object authorities = user.get(AUTHORITIES);
                    List<SimpleGrantedAuthority> permissions = new ArrayList<>();

                    if (authorities instanceof List<?>) {
                        permissions = ((List<?>) authorities).stream()
                                .filter(String.class::isInstance)
                                .map(String.class::cast)
                                .map(SimpleGrantedAuthority::new)
                                .toList();
                    }

                    return new UsernamePasswordAuthenticationToken(user, null, permissions);
                } else {
                    throw new RuntimeException("Authentication failed");
                }
            }
            return null;
    }

}
