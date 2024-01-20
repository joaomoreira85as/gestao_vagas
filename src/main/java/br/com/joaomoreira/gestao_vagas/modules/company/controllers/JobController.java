package br.com.joaomoreira.gestao_vagas.modules.company.controllers;

import br.com.joaomoreira.gestao_vagas.modules.company.dtos.CreateJobDTO;
import br.com.joaomoreira.gestao_vagas.modules.company.entities.JobEntity;
import br.com.joaomoreira.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/companies/jobs")
@RequiredArgsConstructor
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        var jobEntity = JobEntity.builder()
                .benefits(jobDTO.getBenefits())
                .level(jobDTO.getLevel())
                .description(jobDTO.getDescription())
                .companyId(UUID.fromString(companyId.toString()))
                .build();
        return createJobUseCase.execute(jobEntity);
    }
}