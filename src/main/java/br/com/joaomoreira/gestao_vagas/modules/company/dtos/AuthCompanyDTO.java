package br.com.joaomoreira.gestao_vagas.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    private String email;
    private String password;
}
