package br.com.joaomoreira.gestao_vagas.modules.company.useCases;

import br.com.joaomoreira.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import br.com.joaomoreira.gestao_vagas.modules.company.dtos.AuthCompanyResponseDTO;
import br.com.joaomoreira.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.joaomoreira.gestao_vagas.utils.TokenJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenJWT tokenJWT;


    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws UsernameNotFoundException {
        var company = this.companyRepository.findByEmail(authCompanyDTO.getEmail()).orElseThrow(()->{
            throw new UsernameNotFoundException("email/password incorrect");
        });

        var isPasswordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(isPasswordMatch == false){
            throw new UsernameNotFoundException("email/password incorrect");
        }
        return this.tokenJWT.generateCompanyToken(company.getId().toString());
    }
}
