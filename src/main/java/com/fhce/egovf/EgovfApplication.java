package com.fhce.egovf;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fhce.egovf.security.jwtAuthorizationFilter;

@SpringBootApplication
public class EgovfApplication  /*extends SpringBootServletInitializer*/{
	
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EgovfApplication.class);
	} para produccion*/

	public static void main(String[] args) {
		SpringApplication.run(EgovfApplication.class, args);
	}
	@Configuration
	@EnableWebSecurity
	class webSecurityConfig extends WebSecurityConfigurerAdapter{
		
		protected void configure(final HttpSecurity http) throws Exception{
			
			jwtAuthorizationFilter jwtFilter =new jwtAuthorizationFilter();
			http.csrf().disable()
			.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			//.antMatchers(HttpMethod.GET,"/fhce/listarUsuario").permitAll()
			//.antMatchers(HttpMethod.POST,"/fhce/loginUsuario").permitAll()
			.antMatchers(HttpMethod.GET,"/fhce-egovf/listarUsuario").permitAll()
			.antMatchers(HttpMethod.POST,"/fhce-egovf/loginUsuario").permitAll()
			.anyRequest().authenticated();
			http.cors();
				
		}
		
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration=new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("http://192.168.31.45:8081/"));
			configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
			configuration.setAllowCredentials(true);
			configuration.setAllowedHeaders(Arrays.asList("*"));
			//configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type","Access-Control-Allow-Origin"));
			//configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
			configuration.setMaxAge(3600L);
			
			UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
			
		}
	}
}
