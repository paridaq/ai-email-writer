package ai_backend.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // disable CSRF if you're testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**", "/").permitAll() // public endpoints
                        .anyRequest().authenticated() // all others need login
                )
                .oauth2Login(oauth2 -> oauth2   // enable OAuth2 login
                        .defaultSuccessUrl("/api/private", true) // redirect to frontend after login
                )
                .formLogin(form -> form.disable()); // 🚫 disable default form login

        return http.build();

    }
}

