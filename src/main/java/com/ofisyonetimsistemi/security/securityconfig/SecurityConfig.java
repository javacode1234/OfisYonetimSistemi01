package com.ofisyonetimsistemi.security.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ofisyonetimsistemi.security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired private MyUserDetailsService userDetailsService;
	@Autowired private MyAuthenticationSuccessHandler authSuccessHandler;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider outhProvider = new DaoAuthenticationProvider();
		outhProvider.setUserDetailsService(userDetailsService);
		outhProvider.setPasswordEncoder(pwdEncoder());
		return outhProvider;
	}
	
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity sec) throws Exception {
		return sec
		.csrf(AbstractHttpConfigurer::disable)
		
		.authorizeHttpRequests(req->{
			req.requestMatchers("/arshahomepage/**", "/niceadminpanel/**", "/customjs/**", "/customcss/**", "/loginimages/**").permitAll();//resources//static
			req.requestMatchers("/", "/login", "/portfolio-details/**", "/service-details/**").permitAll();
			req.requestMatchers("/user-register-request/**", "/send-message/**", "/add-to-mail-list/**").permitAll();
			
			req.requestMatchers("/api/v1/**").hasRole("ADMIN");
			
			req.requestMatchers("/api/v1/admin/**").hasRole("ADMIN");
			req.requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN", "USER");
			req.requestMatchers("/api/v1/customer/**").hasAnyRole("ADMIN", "USER", "CUSTOMER");
			req.anyRequest().authenticated();
			
		})
		
		.formLogin(login->{
			login
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.failureUrl("/login?error").permitAll()
			//.defaultSuccessUrl("/admin/home")
			.successHandler(authSuccessHandler)
			
			.permitAll();
					
			
		})
		//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
		
		.logout(logout->{
			logout.logoutUrl("/logout");
			logout.logoutSuccessUrl("/login?logout").permitAll();
		})
		
		.build();
	}	
	

}
