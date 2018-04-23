/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stonetea.security;

import com.stonetea.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * @author jitendra on 3/3/16.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.requestCache()
				.requestCache(new NullRequestCache())
				.and()
				.httpBasic();*/

		http.csrf().disable();
		http.exceptionHandling()
				.accessDeniedHandler(new GoAccessDeniedHandler())
				.authenticationEntryPoint(new GoAuthenticationEntryPoint())
				.and()
				.authorizeRequests()
				.antMatchers("/login","/css/**", "/js/**","/fonts/**").permitAll()
				.antMatchers("/resources/**","/api/hello/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.requestCache()
				.requestCache(new NullRequestCache()).and()
				.formLogin()
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("pwd").permitAll()
				//.successHandler(successHandler()).permitAll()
				.successHandler(new GoAuthenticationSuccessHandler())
				.failureHandler(new GoAuthenticationFailureHandler())
				.and()
				.logout().logoutSuccessHandler(new GoLogoutSuccessHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").permitAll();
				//.and()
				//.httpBasic();

	}

	// @formatter:off
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**","/api/hello/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll().successHandler(successHandler())
				.and()
			.logout().permitAll();
	}
*/
	// @formatter:on

	// @formatter:off
	//@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.jdbcAuthentication()
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select sex,address,id from t_user where user_name = ? limit 1");
		//auth.userDetailsService(new CustomUserService());
		/*
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");*/
	}

	@Autowired
	private DataSource dataSource;

	/*/
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandlerImpl();
	}*/


	//@Bean
	/*
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}*/

	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public CustomUserService springDataUserDetailsService() {
		return new CustomUserService();
	}
	// @formatter:on
}
