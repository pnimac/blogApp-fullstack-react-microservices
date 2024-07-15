package com.pnimac.auth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.pnimac.auth.filter.JwtAuthenticationFilter;
import com.pnimac.auth.model.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private final JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	private final CustomUserDetailsService userDetailsService;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, CustomUserDetailsService userDetailsService) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//We're using BCrypt for password hashing.
	}

	/**
	 * We're allowing CORS for the React client running on http://localhost:3000.
	 * We're disabling CSRF protection as it's typically not needed for stateless JWT authentication.
	 * Allowing public access to /api/auth/** endpoints (for login/register), and
	 * requiring authentication for all other requests. 
	 * Setting the session creation policy to STATELESS since we're using JWTs. 
	 * Adding a custom JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter. 
	 * @param http
	 * @return
	 * @throws Exception
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	/**	http
		.cors(cors -> cors
				.and()
				.csrf(csrf -> csrf.disable()))
				.authorizeHttpRequests(authz -> authz
					.requestMatchers("/api/auth/**").permitAll()
					.anyRequest().authenticated())
				.sessionManagement(
						sessionManagement -> sessionManagement
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
								.and().userDetailsService(userDetailsService)
								.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class));**/
		http
        .cors().and()
        .csrf().disable()
        .authorizeRequests(authz -> authz
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .userDetailsService(userDetailsService)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * We're exposing the AuthenticationManager as a bean to be used in other components.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // React app's URL
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

}
