package travel.travel_agency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import travel.travel_agency.entities.Role;
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
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/profile").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/all_tour").hasAuthority("USER")
                .requestMatchers("/admin", "/new_tour_form").hasAuthority("ADMIN")
                //Доступ разрешен всем пользователей
                //.requestMatchers("/").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().hasAuthority("ADMIN")
                .and()
                //Настройка для входа в систему
                .formLogin()
                //.loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/profile",true)
                .and()
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.authenticationProvider(authProvider())
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
