package com.example.employee.jwt;


import com.example.employee.Util.ValidateUtil;
import com.example.employee.model.User;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtTokenUtil.validateJwtToken(jwt)) {
                String username = jwtTokenUtil.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain)
//            throws ServletException, IOException {
//        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        String username = null;
//        String jwtToken = null;
//
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//            try {
//                username = jwtTokenUtil.getUserNameFromJwtToken(jwtToken);
//            } catch (IllegalArgumentException e) {
//                log.error("Unable to get JWT Token");
//            } catch (ExpiredJwtException e) {
//                log.error("JWT Token has expired");
//            }
//        }
//
//        // Once we get the token. Validate it
//        if (Objects.nonNull(username)) {
//
//            // TODO: recheck
//            Optional<String> loggedUsernameOpt = Optional
//                    .ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                    .map(Authentication::getName);
//
//            if (!loggedUsernameOpt.isPresent() || !loggedUsernameOpt.get().equals(username)) {
//                Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
//                if (claims.getExpiration().before(new Date())) {
//                    throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.TOKEN_INVALID);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return headerAuth;
    }

}