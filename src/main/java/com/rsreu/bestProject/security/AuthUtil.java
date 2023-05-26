package com.rsreu.bestProject.security;

import com.rsreu.bestProject.data.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

public class AuthUtil {

    public static UserInfo getUserFromRequest(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return (UserInfo) request.getUserPrincipal();
        }
        return null;

    }
}
