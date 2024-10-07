package br.com.gerenciador.tarefas.config;

import br.com.gerenciador.tarefas.filter.FilterAuthentication;
import br.com.gerenciador.tarefas.filter.LoginFilter;
import br.com.gerenciador.tarefas.permissions.PermissionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test-api").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test-api-hello-world").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority(PermissionEnum.USER.toString())
                        .requestMatchers(HttpMethod.POST, "/users").hasAnyAuthority(PermissionEnum.ADMIN.toString())
                        .anyRequest()
                        .authenticated());

        httpSecurity.addFilterBefore(new LoginFilter("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new FilterAuthentication(), UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

}
