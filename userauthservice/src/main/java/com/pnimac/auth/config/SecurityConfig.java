package com.pnimac.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import com.pnimac.auth.model.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	public CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/home", "/public/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/login", "/signup", "/saveNewUser", "/validatelogin", "/dashboard").permitAll()
				.requestMatchers("/add-post", "/delete-post")
				.hasAuthority("BLOGUSER")
				.requestMatchers("/add-comment", "/delete-comment").hasAuthority("BLOGUSER")
				.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
				.loginPage("/login").permitAll())
				//.rememberMe(Customizer.withDefaults())
				.logout(logout -> logout.permitAll()
				.invalidateHttpSession(true));
		return http.build();
	}
	

    @Bean
    public AuthenticationManager authManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(bcryptEncoder());
        return new ProviderManager(authProvider);
    }
	
   
    
    
}
