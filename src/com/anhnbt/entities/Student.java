package com.anhnbt.entities;

import java.io.Serializable;

public class Student extends Person implements Serializable {
    public static int nextId = 1;
    private final int id;

    public Student() {
        // Goi constructor de khoi tao gia tri mac dinh;
        this(Student.nextId, "Chưa có tên", "Chưa có địa chỉ", "Chưa có số điện thoại", "Chưa có email");
    }

    public Student(int id, String name, String address, String phone, String email) {
        super(name, address, phone, email);
        this.id = id;
        nextId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Student{" +
                "id=" + id +
                '}';
    }
}
