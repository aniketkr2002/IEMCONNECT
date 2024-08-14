package com.iemconnect.iemconnect.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iemconnect.iemconnect.config.UserDetailsServiceImpl;

import java.io.IOException;

@Component
public class ConnectJWTAuthFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(ConnectJWTAuthFilter.class);
    @Autowired
    private ConnectJWTHelper connectJWTHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        logger.info("Header : {}", requestHeader);
        String userName = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
            try {
                userName = connectJWTHelper.extractUsername(token);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument While fetching the Username !!!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given Token is Expired !!!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changes have been made in the token !! Invalid token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            boolean isValidToken = connectJWTHelper.validateToken(token, userDetails);
            if (isValidToken) {
                logger.info("Valid Token for request: {}", request);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                logger.info("Validation fails !!");
            }
        }

        filterChain.doFilter(request, response);
    }
}

