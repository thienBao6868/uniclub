package com.Thienbao.uniclub.filter;

import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

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
                boolean isSuccess = jwtHelper.decodeToken(token);
                if(isSuccess){

                    UsernamePasswordAuthenticationToken tokenAuthen = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());

                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(tokenAuthen);
                }
            }
        }



        filterChain.doFilter(request,response);
    }
}
