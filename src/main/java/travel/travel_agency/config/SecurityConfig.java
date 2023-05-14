package travel.travel_agency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import travel.travel_agency.services.UserService;
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final UserService service;

    public SecurityConfig(@Lazy UserService service) {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/registration",  "/**.js").permitAll()
                .requestMatchers("/profile","/buy_new_tour/**","/buy_new_tour")
                .hasAnyAuthority("USER", "ADMIN")
                .anyRequest().hasAuthority("ADMIN")
                .and()
                .formLogin()
                .defaultSuccessUrl("/profile",true)
                .and()
                .logout()
                .logoutUrl("/logout");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
