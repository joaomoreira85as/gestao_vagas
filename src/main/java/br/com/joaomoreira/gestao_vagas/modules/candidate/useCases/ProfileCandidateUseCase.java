package br.com.joaomoreira.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import br.com.joaomoreira.gestao_vagas.modules.candidate.dtos.ProfileCandidateResponseDTO;
import br.com.joaomoreira.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileCandidateUseCase {
    private final CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();

        return candidateDTO;
    }
}
