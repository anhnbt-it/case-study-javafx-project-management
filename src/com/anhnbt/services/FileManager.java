package com.anhnbt.services;

import com.anhnbt.entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnhNBT (anhnbt.it@gmail.com)
 * Date: 09/11/2020
 * Time: 8:42 SA
 */

public class FileManager {
    private final String pathName = "data.dat";
    private File file;

    public FileManager() {
        file = new File(pathName);
    }

    public ArrayList<Student> readFromFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object students = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return (ArrayList<Student>) students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFile(List<Student> students) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(students);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExcel(List<Student> students) throws Exception {
        Writer writer = null;
        try {
            File file = new File("Data.csv.");
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(file));
            for (Student student: students) {
                String text = student.getName() + "," + student.getAddress() + "," + student.getPhone() + "," + student.getEmail() + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
    }
}
