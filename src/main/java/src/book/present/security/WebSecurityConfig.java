package src.book.present.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final JwtTokenProvider jwtTokenProvider;

    private final MyUserDetails myUserDetails;

    @Autowired
    public WebSecurityConfig(MyUserDetails myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
//        http.authorizeRequests()//
//                .antMatchers("/user").permitAll()
//                .antMatchers("/auth/login").permitAll()//
//                .antMatchers("/auth/signup").permitAll()//
//                .antMatchers("/h2-console/**/**").permitAll()
                // Disallow everything else..
//                .anyRequest().permitAll();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

//         Apply JWT
        http.apply(new JwtTokenFilterConfigurer(new JwtTokenFilter(myUserDetails)));

        // Optional, if you want to test the API from a browser
        http.httpBasic();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
