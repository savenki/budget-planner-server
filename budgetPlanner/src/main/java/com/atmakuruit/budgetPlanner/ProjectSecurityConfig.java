package com.atmakuruit.budgetPlanner;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	 @SuppressWarnings("removal")
	    @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeHttpRequests()
	                .requestMatchers(PathRequest.toH2Console()).permitAll()
	                .requestMatchers("/", "/error", "/budgetPlanner/**").permitAll()
	                .requestMatchers("/actuator/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
	            .and()
	                .formLogin()
	            .and()
	                .httpBasic();

	        http.headers().frameOptions().disable();
	        return http.build();
	    }

	    @Bean
	    public InMemoryUserDetailsManager userDetailService() {
	        UserDetails user = User.withDefaultPasswordEncoder()
	                .username("kavya")
	                .password("123456")
	                .roles("USER")
	                .build();

	        UserDetails admin = User.withDefaultPasswordEncoder()
	                .username("venky")
	                .password("54321")
	                .roles("USER", "ADMIN")
	                .build();

	        return new InMemoryUserDetailsManager(user, admin);
	    }

	
	/**
	 * Permit all http pages without security
	 * http.authorizeHttpRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();
	 */
	
	/**
	 * deny all http pages without security
	 * http.authorizeHttpRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();
	 */
	
	/**
	 * RequestMatcher used for custom path permit or deny
	 * http.authorizeHttpRequests().requestMatchers("/budgetPlanner").permitAll()
		.and().formLogin().and().httpBasic();
		requestMatchers("/budgetPlanner", "/budgetPlanner/timePeriod").permitAll() can also be in one line with comma seperated
		
	 */
	
	/**
	 * CSRF how to disable cross site resource forgery
	 * http.csrf().disable()
	 */
}

