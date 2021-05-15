package com.agreggio.challenge.birras.santander.meet.up.configuration;


import com.agreggio.challenge.birras.santander.common.configuration.CustomAuthenticationEntryPoint;
import com.agreggio.challenge.birras.santander.common.configuration.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.headers().frameOptions().disable();
		 http.csrf().disable()
				 .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).and()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.GET,
                    "/v2/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui.html**",
                    "/webjars/**",
                    "favicon.ico").permitAll()
				 .antMatchers("/weather/**").hasAnyRole("USER","ADMIN")
				 .antMatchers("/confirmGuest/**").hasAnyRole("USER","ADMIN")
				 .antMatchers("/userCheckIn/**").hasAnyRole("USER","ADMIN")
				 .antMatchers("/**").hasRole("ADMIN");
	}



}
