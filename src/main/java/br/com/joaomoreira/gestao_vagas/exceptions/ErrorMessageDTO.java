package br.com.joaomoreira.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessageDTO {
    private String message;
    private String field;
}
