package br.com.joaomoreira.gestao_vagas.exceptions;

import java.util.concurrent.ExecutionException;

public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("Usuário já existe!");
    }
}
