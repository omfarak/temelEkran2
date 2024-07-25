package adminNew.temelEkran.config;

import adminNew.temelEkran.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/school/**").hasRole("SCHOOL");
                    auth.requestMatchers("/students/**").hasRole("STUDENTS");
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .permitAll()
                )
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
