package com.DemoSpring.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/city").hasAuthority("ADMIN")
			.antMatchers("/admin").hasAuthority("ADMIN")
			.antMatchers("/user").hasAuthority("SELLER")
			.antMatchers("/editor").hasAuthority("CUSTOMER")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.and()
			.logout()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
	}
	
	
	// hasRole with sql ->>> ROLE_ADMIN
	// hasAuthority with sql ->>> ADMIN
}
