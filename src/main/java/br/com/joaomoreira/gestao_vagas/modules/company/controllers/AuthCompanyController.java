package br.com.joaomoreira.gestao_vagas.modules.company.controllers;

import br.com.joaomoreira.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import br.com.joaomoreira.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;
    @PostMapping("/auth")
    public ResponseEntity create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
