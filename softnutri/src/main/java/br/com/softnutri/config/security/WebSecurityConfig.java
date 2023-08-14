package br.com.softnutri.config.security;

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

import br.com.softnutri.config.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
        .requestMatchers("/user/auth/**").permitAll()
        .requestMatchers("/patient/").permitAll()
		.requestMatchers("/patient/save").permitAll()
		.requestMatchers("/patient/get/**").permitAll()
		.requestMatchers("/patient/delete/**").permitAll()
		.requestMatchers("/professional/").permitAll()
		.requestMatchers("/professional/save").permitAll()
		.requestMatchers("/professional/get/**").permitAll()
		.requestMatchers("/professional/delete/**").permitAll()
		.requestMatchers("/professional/nutritionist").permitAll()
		.requestMatchers("/food/").permitAll()
		.requestMatchers("/food/save").permitAll()
		.requestMatchers("/food/delete/**").permitAll()
		.requestMatchers("/foodBunch/").permitAll()
		.requestMatchers("/foodBunch/save").permitAll()
		.requestMatchers("/foodBunch/delete/**").permitAll()
		.requestMatchers("/bunch/").permitAll()
		.requestMatchers("/bunch/save").permitAll()
		.requestMatchers("/bunch/delete/**").permitAll()
		.requestMatchers("/calendar/").permitAll()
		.requestMatchers("/calendar/save").permitAll()
		.requestMatchers("/calendar/cancel/**").permitAll()
		.requestMatchers("/calendar/get/**").permitAll()
		.requestMatchers("/calendar/professional").permitAll()
        .anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
