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
	
	// 요청에 대한 접근여부, 접근권한을 지정한다.
	// 로그인페이지, 로그인시 필요한 파라미터, 로그인 성공/실패시 이동할 URL을 지정
	// 로그아웃 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
       http
        .authorizeRequests()
        .antMatchers("/", "/register", "/completed").permitAll()				// 인증없이 접근 허용
        .antMatchers("/book/**").permitAll()									// 인증없이 접근 허용
        .antMatchers("/member/**", "/cart/**", "/order/**").hasRole("USER")		// 인증이 필요하고, ROLE_USER 권한이 있는 경우 접근 허용
        .antMatchers("/admin/**").hasRole("ADMIN")								// 인증이 필요하고, ROLE_ADMIN 권한이 있는 경우 접근 허용
        .anyRequest().authenticated()											// 위에서 나열한 요청 외의 모든 요청은 무조건 인증없이 접근할 수 없다.
        .and()
        	.formLogin()
        	.loginPage("/login")												// 사용자정의 로그인페이지를 요청하는 URI 지정
    		.loginProcessingUrl("/login")										// 로그인처리를 요청하는 URI 지정, 로그인폼의 <form action="/login" method="post" >
        	.usernameParameter("email")											// 사용자식별정보는 email이라는 이름으로 서버에 전달된다.
        	.passwordParameter("password")										// 사용자비밀번호는 password라는 이름으로 서버에 전달된다
        	.failureUrl("/login?error=failure")									// 로그인 실패했을 때 리다이렉트할 URI 지정
        	.permitAll()														// 위에서 설정한 /login, /login?error=failure는 인증없이 접근 허용
        .and()
        	.logout()
        	.logoutUrl("/logout")												// 로그아웃처리를 요청하는 URI 지정
        	.logoutSuccessUrl("/")												// 로그아웃 성공했을 때 리다이렉트할 URI 지정
        	.permitAll();														// 인증없이 접근 허용
	}

	// 인증이 필요없는 요청URI를 지정한다.
	// 정적자원(이미지, css, js)을 요청하는 것과 h2 데이터베이스 콘솔화면을 요청하는 URI는 인증없이 접근하도록 설정한다.
	@Override
	public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/**", "/h2-console/**");
	}
   
	// 인증관리자가 사용할 UserDetailsService구현객체와 비밀번호인코딩 객체를 지정한다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
