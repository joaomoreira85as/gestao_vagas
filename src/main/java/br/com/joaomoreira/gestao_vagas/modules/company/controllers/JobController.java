package br.com.joaomoreira.gestao_vagas.modules.company.controllers;

import br.com.joaomoreira.gestao_vagas.modules.company.entities.JobEntity;
import br.com.joaomoreira.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    @PostMapping
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
        return createJobUseCase.execute(jobEntity);
    }
}