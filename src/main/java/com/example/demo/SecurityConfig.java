package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((auth) ->
			auth.requestMatchers("/sayHello").authenticated()
			.anyRequest().permitAll()
			
		)
		.formLogin((formLogin) -> formLogin.usernameParameter("user").passwordParameter("pwd"))  // THis is necessary when we need a login page
		.httpBasic(hp -> hp.realmName("dummy")); // is must to do basic authentication through api call
		return http.build();
		
	}
	
	// This approach we can create multiple in-memory users
	@Bean
	public InMemoryUserDetailsManager setUsers() {
//		UserDetails user1= User.withDefaultPasswordEncoder().username("sai").password("1234").authorities("USER").build();
//		UserDetails user2= User.withDefaultPasswordEncoder().username("veeru").password("divya").authorities("ADMIN").build();
		
		//Password Encoder bean is separately created so here not required
		UserDetails user1= User.withUsername("sai").password("1234").authorities("USER").build();
		UserDetails user2= User.withUsername("veeru").password("divya").authorities("ADMIN").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}

	
	@Bean
	public PasswordEncoder getPwEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
