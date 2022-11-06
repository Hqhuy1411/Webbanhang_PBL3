package com.DemoSpring.Security;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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

	@Bean("messageResourceSB")
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
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
		/*
		 * http .authorizeHttpRequests() .antMatchers("/").permitAll()
		  .antMatchers("/product/**").permitAll()
		  .antMatchers("/city").hasRole("ADMIN")
		  .antMatchers("/admin").hasRole("ADMIN")
		  .antMatchers("/seller/**").hasRole("SELLER")
		  .antMatchers("/customer/**").hasRole("CUSTOMER")
		  .antMatchers("/images/**").permitAll() .anyRequest().permitAll() .and()
		 * .formLogin() .loginPage("/login")
		 * 
		 * // .usernameParameter("username") // .passwordParameter("password") //
		 * .loginProcessingUrl("/product") // .defaultSuccessUrl("/product") //
		 * .failureUrl("/login.html?error=true") .and() .logout() .and()
		 * .exceptionHandling().accessDeniedPage("/403") .and() .httpBasic();
		 */
		http
        .authorizeRequests()
           .antMatchers("/", "/home").permitAll()
           .antMatchers("/product/**").permitAll()
 		   .antMatchers("/city").hasRole("ADMIN")
 		   .antMatchers("/admin").hasRole("ADMIN")
 		   .antMatchers("/seller/**").hasRole("SELLER")
 		   .antMatchers("/customer/**").hasRole("CUSTOMER")
 		   .antMatchers("/register").permitAll()
 		  .antMatchers("/resources/**").permitAll()
 		   .antMatchers("/images/**").permitAll()
           //.anyRequest().authenticated()
           .and()
        .formLogin()
           .loginPage("/login")
           .defaultSuccessUrl("/product")
           .permitAll()
           .and()
           .logout()
           .permitAll().and()
		   .exceptionHandling().accessDeniedPage("/403") .and() .httpBasic();
	}

	// hasRole with sql ->>> ROLE_ADMIN
	// hasAuthority with sql ->>> ADMIN
}
