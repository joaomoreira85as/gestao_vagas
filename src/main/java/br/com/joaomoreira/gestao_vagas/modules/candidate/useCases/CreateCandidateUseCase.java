package br.com.joaomoreira.gestao_vagas.modules.candidate.useCases;

import br.com.joaomoreira.gestao_vagas.exceptions.UserFoundException;
import br.com.joaomoreira.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.joaomoreira.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCandidateUseCase {
    private final PasswordEncoder passwordEncoder;
    private final CandidateRepository repository;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.repository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) ->{
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        return this.repository.save(candidateEntity);
    }
}
