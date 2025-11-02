package com.stocksense.stocksense.common.utils;

import com.stocksense.stocksense.common.exceptions.UnAuthorizedException;
import com.stocksense.stocksense.common.model.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUtilsService {
    public AuthUser getCurrentUser() {
        try {
            return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new UnAuthorizedException("Unauthorized");
        }
    }
}
