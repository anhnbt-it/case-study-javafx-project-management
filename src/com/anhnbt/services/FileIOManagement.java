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
    private final String pathToFolder = "C:\\Users\\KhoaiTay\\OneDrive\\javafx-project-management";
    private final String csvFile = "Data.csv";
    private final String cvsSplitBy = ",";
    private File file;

    public FileIOManagement() {}

    public boolean writeCSVFile(List<Student> students) {
        try {
            file = new File(csvFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            Writer writer = new BufferedWriter(new FileWriter(file));
            for (Student student : students) {
                String text = student.getId() + "," + student.getName() + "," + student.getAddress() + "," + student.getPhone() + "," + student.getEmail() + "\n";
                try {
                    writer.write(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> readCSV() {
        List<Student> students = new ArrayList<>();
        try {
            file = new File(csvFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains(cvsSplitBy)) {
                    String[] data = line.split(cvsSplitBy);
                    students.add(new Student(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
