package com.anhnbt.services;

import com.anhnbt.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentIManagement implements IManagement<Student> {
    private List<Student> students;

    public StudentIManagement() {
        students = new ArrayList<>();
        students.add(new Student(Student.nextId, "Nguyễn Bá Tuấn Anh", "Hà Nội", "0346868928", "anhnbt.it@gmail.com"));
        students.add(new Student(Student.nextId,"Trần Công Duy", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Thanh Khiên", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Hoàng Việt Anh", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Phạm Thái Hoàng", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Công Hiếu", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Lê Quang Duy", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Hà Học Quang", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Hồ Đức Hải", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Phạm Tuấn Anh", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Trần Sỹ Nguyên", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Khánh Duy", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Quang Mạnh Dũng", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Thị Thu Cúc", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Hữu Thọ", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Lê Đức Linh", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Minh Đức", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
        students.add(new Student(Student.nextId,"Nguyễn Thanh Tùng", "Chưa nhập", "Chưa nhập", "Chưa nhập"));
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
