package com.rsreu.bestProject.security;

import com.rsreu.bestProject.data.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {

    public static UserInfo getUserFromContext(HttpServletRequest request) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            return (UserInfo) user;
        }
        return null;

    }
}
