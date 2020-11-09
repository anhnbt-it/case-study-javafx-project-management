package com.anhnbt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private List<Student> students;

    public StudentDao() {
        students = new ArrayList<>();
        students.add(new Student("Tuan Anh", "Ha Noi", "0346868928", "anhnbt.it@gmail.com"));
        students.add(new Student("Khoai Tay", "Ha Noi", "Chưa nhập", "Chưa nhập"));
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public Student get(int id) {
        return students.get(id);
    }

    @Override
    public boolean save(Student obj) {
        return students.add(obj);
    }

    @Override
    public Student update(int index, Student obj) {
        return students.set(index, obj);
    }

    @Override
    public Student delete(int index) {
        return students.remove(index);
    }

    @Override
    public boolean delete(Student obj) {
        return students.remove(obj);
    }
}
