package com.employee.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.employee.management.serviceImp.EmployeeUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new EmployeeUserDetailService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().authorizeRequests()
		        .antMatchers(HttpMethod.GET, "/employee/**","/login/**")
				.hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST  ,"/employee/**","/user/**")
				.hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT  ,"/employee/**")
				.hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE  ,"/employee/**")
				.hasRole("ADMIN")
				.antMatchers(HttpMethod.GET  ,"/user/**")
				.hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/employee/list", true).and()
				.httpBasic();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
