package br.com.joaomoreira.gestao_vagas.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityCompanyFilter securityCompanyFilter;
    private final SecurityCandidateFilter securityCandidateFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers(HttpMethod.POST,"/candidates").permitAll()
                            .requestMatchers(HttpMethod.POST,"/companies").permitAll()
                            .requestMatchers(HttpMethod.POST,"/companies/auth").permitAll()
                            .requestMatchers(HttpMethod.POST,"/candidates/auth").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
