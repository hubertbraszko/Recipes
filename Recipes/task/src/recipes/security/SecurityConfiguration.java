package recipes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(getEncoder().encode("pass1")).roles()
                .and()
                .withUser("user2").password(getEncoder().encode("pass2")).roles("USER")
                .and()
                .withUser("user3").password(getEncoder().encode("pass3")).roles("ADMIN")
                .and()
                .passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/api/recipe/new").authenticated()
                .mvcMatchers(HttpMethod.GET ,"/api/recipe/*").permitAll()
                .and().httpBasic().and().formLogin().and().csrf().disable().headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
