package gov.iti.career.hub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwksUri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                r -> r.jwt(jwtConfigurer -> jwtConfigurer
                                .jwkSetUri(jwksUri)
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
                        )
        );

        http.authorizeHttpRequests( reg ->
                reg.requestMatchers("/student/register").permitAll()
                    .requestMatchers("/company/register").permitAll()
                    .requestMatchers("/staff/register").permitAll()
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
        );

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}