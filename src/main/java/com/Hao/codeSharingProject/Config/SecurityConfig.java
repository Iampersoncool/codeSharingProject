package com.Hao.codeSharingProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers()
			.xssProtection()
			.and()
			.contentSecurityPolicy("default-src 'self' cdnjs.cloudflare.com;");
		
		return http.build();
	}
}
