package br.com.joaomoreira.gestao_vagas.modules.candidate.controllers;

import br.com.joaomoreira.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.joaomoreira.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.joaomoreira.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;


    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping()
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase
                    .execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
