package com.volunteacher.app.config.SecurityClasses;

import static com.volunteacher.app.config.SecurityClasses.SecurityConstant.PUBLIC_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
//@CrossOrigin(origins="http://localhost:4200")  
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Bean
	BCryptPasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("AAVO");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:4200"));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setExposedHeaders(List.of("totalKids"));
            return cors;
          	}).and().headers().and().authorizeRequests()
                .antMatchers(PUBLIC_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    
}
