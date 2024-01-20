package br.com.joaomoreira.gestao_vagas.modules.company.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateJobDTO {
    @NotBlank(message = "Esse campo é obrigatório")
    private String description;
    @NotBlank(message = "Esse campo é obrigatório")
    private String benefits;
    @NotBlank(message = "Esse campo é obrigatório")
    private String level;
}
