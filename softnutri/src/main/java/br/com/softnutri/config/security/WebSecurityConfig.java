package br.com.softnutri.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.softnutri.config.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

		http.authorizeHttpRequests().requestMatchers("/user/auth/**").anonymous()
		.requestMatchers("/patient/").permitAll()
		.requestMatchers("/patient/save").permitAll()
		.requestMatchers("/patient/get/**").permitAll()
		.requestMatchers("/patient/delete/**").permitAll()
		.requestMatchers("/professional/").permitAll()
		.requestMatchers("/professional/save").permitAll()
		.requestMatchers("/professional/get/**").permitAll()
		.requestMatchers("/professional/delete/**").permitAll()
		.requestMatchers("/food/").permitAll()
		.requestMatchers("/food/save").permitAll()
		.requestMatchers("/food/delete/**").permitAll()
		.requestMatchers("/foodBunch/").permitAll()
		.requestMatchers("/foodBunch/save").permitAll()
		.requestMatchers("/foodBunch/delete/**").permitAll()
		.requestMatchers("/bunch/").permitAll()
		.requestMatchers("/bunch/save").permitAll()
		.requestMatchers("/bunch/delete/**").permitAll()
		.anyRequest().authenticated().and().authenticationManager(authenticationManager)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic().and().formLogin().and().logout().and().csrf().disable();
		return http.build();
	}
}
