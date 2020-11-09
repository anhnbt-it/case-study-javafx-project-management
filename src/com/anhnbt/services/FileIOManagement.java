package com.anhnbt.services;

import com.anhnbt.entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIOManagement {
    private final String csvFile = "Data.csv";
    private final File file = new File(csvFile);

    public FileIOManagement() {}

    public boolean writeCSVFile(List<Student> students) {
        try {
            if (!file.exists()) file.createNewFile();
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
            if (!file.exists()) file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String cvsSplitBy = ",";
                if (line.contains(cvsSplitBy)) {
                    String[] data = line.split(cvsSplitBy);
                    students.add(new Student(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
