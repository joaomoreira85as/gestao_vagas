package br.com.joaomoreira.gestao_vagas.modules.candidate.controllers;

import br.com.joaomoreira.gestao_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import br.com.joaomoreira.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class AuthCandidateController {

    private final AuthCandidateUseCase authCompanyUseCase;
    @PostMapping("/auth")
    public ResponseEntity create(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var result = this.authCompanyUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
