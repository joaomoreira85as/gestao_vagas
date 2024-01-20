package br.com.joaomoreira.gestao_vagas.utils;

import br.com.joaomoreira.gestao_vagas.modules.candidate.dtos.AuthCandidateResponseDTO;
import br.com.joaomoreira.gestao_vagas.modules.company.dtos.AuthCompanyResponseDTO;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class TokenJWT {

    @Value("${security.token.secret}")
    private String secretKey;
    @Value("${security.token.expiration-hours}")
    private Integer expirationInHours;

    public AuthCompanyResponseDTO generateCompanyToken(String id){
        var expiresIn = Instant.now().plus(Duration.ofHours(expirationInHours));
        var claims = Arrays.asList("COMPANY");
        var token = generateToken(secretKey, expiresIn, id, claims);
        return AuthCompanyResponseDTO.builder()
                .accessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
    }

    public AuthCandidateResponseDTO generateCandidateToken(String id){
        var claims = Arrays.asList("CANDIDATE");
        var expiresIn = Instant.now().plus(Duration.ofHours(expirationInHours));
        var token =  generateToken(secretKey, expiresIn, id, claims);
        return AuthCandidateResponseDTO.builder()
                .accessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
    }

    private String generateToken (String secretKey, Instant expirationInHours, String id, List<String> claims) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = com.auth0.jwt.JWT.create().withIssuer("javagas")
                .withExpiresAt(expirationInHours)
                .withSubject(id)
                .withClaim("roles", claims)
                .sign(algorithm);
        return token;
    }
}
