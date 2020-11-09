package com.anhnbt.services;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    private static final Map<String, String> USERS = new HashMap<>();
    static {
        USERS.put("admin", "123");
    }
    public static boolean validate(String userId, String password) {
        String validUserPassword = USERS.get(userId);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
