package br.com.joaomoreira.gestao_vagas.modules.candidate.useCases;

import br.com.joaomoreira.gestao_vagas.modules.candidate.dtos.AuthCandidateResponseDTO;
import br.com.joaomoreira.gestao_vagas.utils.TokenJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.joaomoreira.gestao_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import br.com.joaomoreira.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
@RequiredArgsConstructor
public class AuthCandidateUseCase {


    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenJWT tokenJWT;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws UsernameNotFoundException {
        var candidate = this.candidateRepository.findByEmail(authCandidateRequestDTO.email()).orElseThrow(()->{
            throw new UsernameNotFoundException("email/password incorrect");
        });

        var isPasswordMatch = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!isPasswordMatch){
            throw new UsernameNotFoundException("email/password incorrect");
        }
        return this.tokenJWT.generateCandidateToken(candidate.getId().toString());
    }
}
