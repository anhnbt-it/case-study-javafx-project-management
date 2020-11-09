package com.anhnbt.services;

import com.anhnbt.entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: AnhNBT (anhnbt.it@gmail.com)
 * Date: 09/11/2020
 * Time: 8:42 SA
 */

public class FileIOManagement {
    private final String pathName = "Data.csv";
    private File file;

    public FileIOManagement() {
        file = new File(pathName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(List<Student> students) throws Exception {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (Student student : students) {
                String text = student.getId() + "," + student.getName() + "," + student.getAddress() + "," + student.getPhone() + "," + student.getEmail() + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public List<Student> readCSV() {
        Scanner scanner = null;
        List<Student> students = new ArrayList<>();
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(",")) {
                    String[] data = line.split(",");
                    students.add(new Student(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return students;
    }
}
