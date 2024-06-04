package com.Thienbao.uniclub.filter;

import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilterSecurity extends OncePerRequestFilter {

    @Autowired
    JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorValue = request.getHeader("Authorization");

        if( authorValue != null && authorValue.startsWith("Bearer ") ){
            String token = authorValue.substring(7);
            if(!token.equals("")){
                boolean check = jwtHelper.decodeToken(token);
                System.out.print(check);
            }
        }



        filterChain.doFilter(request,response);
    }
}
