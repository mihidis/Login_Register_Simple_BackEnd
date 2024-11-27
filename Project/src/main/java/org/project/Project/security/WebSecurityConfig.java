package org.project.Project.security;

import org.project.Project.util.constants.Authorities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    
    private static final String[] WHITELIST = {
        "/",
        "/login",
        "/info",
        "/db-console/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
        .authorizeRequests(requests -> requests
            .requestMatchers(WHITELIST).permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/admin/**").hasAuthority(Authorities.ACCESS_ADMIN_PANEL.getAuthorityString())
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
        )
        .httpBasic();
        /* 
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers(WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/login?error")
                        .permitAll()
                        .and()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("logout?success")
                        .and()
                        .httpBasic());*/
                        

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions().disable());
        return http.build();
    }

}
