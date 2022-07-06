package src.book.api.middleware;

import org.springframework.web.filter.OncePerRequestFilter;
import src.book.exception.appException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class jwt extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        filterChain.doFilter(request, response);
        try {

        } catch (appException ex) {

        }
    }

    private boolean validateToken(String token) {
        return true;
    }
}
