package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.common.constant.Headers;
import com.stocksense.stocksense.common.constant.UserCacheKey;
import com.stocksense.stocksense.common.model.AuthUser;
import com.stocksense.stocksense.common.utils.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AuthUserService authUserService;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(Headers.AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith(Headers.AUTH_HEADER_STARTER)) {
            String userEmail = jwtUtil.extractToken(authHeader.substring(7));
            if (!userEmail.isEmpty()) {
                AuthUser authUser = (AuthUser) redisService.getData(UserCacheKey.of(userEmail).toString());
                if (authUser == null) {
                    authUser = authUserService.getAuthUserByEmail(userEmail);
                }
                if (authUser != null) {
                    redisService.saveData(UserCacheKey.of(userEmail).toString(), authUser);
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(new AuthenticatedUser(authUser));
                    SecurityContextHolder.setContext(securityContext);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
