package com.pty.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pty.web.common.CustomAuthenticationFailureHandler;
import com.pty.web.common.CustomAuthenticationSuccessHandler;
import com.pty.web.service.member.CustomUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/member/**")
				.hasAuthority("MEMBER") 
			.and()
				.formLogin()
				.loginPage("/securelogin")
				.successHandler(customAuthenticationSuccessHandler).failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("memberId")
				.passwordParameter("memberPassword")
				.permitAll()
			.and()
				.logout() // 로그아웃 사용
				.logoutSuccessUrl("/") // 로그아웃 시 가지는 페이지
				.invalidateHttpSession(true) // 세션 초기화
				.deleteCookies("JSESSIONID"); // 쿠키 삭제
	}
}
