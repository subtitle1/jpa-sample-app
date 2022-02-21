package com.example.storeweb.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecuritySonfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
        .authorizeRequests()
        .antMatchers("/", "/register", "/completed").permitAll()
        .antMatchers("/book/**").permitAll()
        .anyRequest().authenticated()
        .and()
        	.formLogin()
        	.loginPage("/login")
    		.loginProcessingUrl("/login")
        	.usernameParameter("email")
        	.passwordParameter("password")
        	.failureUrl("/login?error=failure")
        	.permitAll()
        .and()
        	.logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/")
        	.permitAll();
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/**", "/h2-console/**");
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
   }
}
