package de.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        super.configure(http);
//        http.authorizeRequests()
//                .anyRequest()
//                .antMatchers(HttpMethod.GET,"/api/display")
//                .denyAll()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/api/logging");
//                .csrf();
    }

}
