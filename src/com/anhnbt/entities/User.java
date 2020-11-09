package com.anhnbt.entities;

import java.util.HashMap;
import java.util.Map;

public class User extends Person {
    private final String userId;
    private static final Map<String, User> USERS = new HashMap<>();

    private User(String userId) {
        this.userId = userId;
    }

    /*
     * Thread Safe Singleton - Design Pattern - Creational Singleton
     * Doi tuong ton tai duy nhat va co the truy xuat moi luc moi noi
     */
    public static User getInstance(String userId) {
        User instance = USERS.get(userId);
        if (instance == null) {
            // Khoi lenh se khong thuc hien neu instance da duoc tao
            synchronized(User.class) { // Dung synchronized de toi uu Multithreading
                if(null == instance) {
                    instance = new User(userId);
                    instance.setName("Nguyễn Bá Tuấn Anh");
                    USERS.put(userId, instance);
                }
            }
        }
        return instance;
    }


    public String getUserId() {
        return userId;
    }
}
