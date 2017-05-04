package ua.pp.disik.tt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.pp.disik.tt.services.MongoUserDetailsService;

/**
 * Created by disik on 5/3/17.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private MongoUserDetailsService mongoUserDetailsService;

    @Autowired
    public void setMongoUserDetailsService(MongoUserDetailsService mongoUserDetailsService) {
        this.mongoUserDetailsService = mongoUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mongoUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                        .antMatchers("/publication/create",
                                "/publication/update/*",
                                "/publication/delete/*").hasRole("USER")
                        .antMatchers("/sign/in",
                                "/sign/up").anonymous()
                        .and().formLogin().loginPage("/sign/in")
                        .and().logout().logoutUrl("/sign/out")
                                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
