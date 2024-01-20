package br.com.joaomoreira.gestao_vagas.modules.company.useCases;

import br.com.joaomoreira.gestao_vagas.exceptions.UserFoundException;
import br.com.joaomoreira.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.joaomoreira.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyUseCase {

    private final CompanyRepository repository;
    private final PasswordEncoder passwordEncoder;
    public CompanyEntity execute(CompanyEntity companyEntity){
            this.repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company) ->{
                    throw new UserFoundException();
                });
            var password = passwordEncoder.encode(companyEntity.getPassword());
            companyEntity.setPassword(password);
        return this.repository.save(companyEntity);
    }
}
