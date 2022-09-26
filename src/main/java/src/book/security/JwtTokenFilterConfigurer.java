package src.book.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import src.book.core.usecases.AuthUseCase;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenFilter jwtTokenFilter;

    private AuthUseCase authUseCase;

    private MyUserDetails myUserDetails;

    public JwtTokenFilterConfigurer(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(authUseCase, myUserDetails);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
