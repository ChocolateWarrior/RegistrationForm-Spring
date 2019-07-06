package de.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select login, password, active from users where login=?")
                .authoritiesByUsernameQuery("select u.login," +
                        " ur.roles from users u inner join user_role ur on" +
                        " u.id=ur.user_id where u.login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/display", "/main", "/")
                .authenticated()
//                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();


    }

}
