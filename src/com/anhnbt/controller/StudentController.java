package com.anhnbt.controller;

import com.anhnbt.Main;
import com.anhnbt.services.FileIOManagement;
import com.anhnbt.services.Validator;
import com.anhnbt.entities.Student;
import com.anhnbt.services.StudentIManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    private FileIOManagement fileIOManagement;
    private Main application;
    private StudentIManagement studentManagement;
    private Validator validator;
    private boolean isEdit = false;
    private int studentId = -1;

    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> addressCol;
    @FXML
    private TableColumn<Student, String> phoneCol;
    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField searchField;

    public void setApplication(Main application) {
        this.application = application;
    }

    public void btnReset(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        name.clear();
        address.clear();
        phone.clear();
        email.clear();
    }

    public void btnAddStudent(ActionEvent actionEvent) {
        if (!validator.name(name.getText())) {
            application.showMsg("Tên phải có độ dài từ 2 - 50 ký tự.", Alert.AlertType.ERROR);
        } else if (!validator.email(email.getText())) {
            application.showMsg("Email sai định dạng!", Alert.AlertType.ERROR);
        } else {
            Student student = new Student(name.getText(), address.getText(), phone.getText(), email.getText());
            if (!isEdit) {
                if (studentManagement.save(student)) {
                    application.showMsg("Thêm học viên thành công!", Alert.AlertType.INFORMATION);
                    tableView.getItems().add(student);
                    clearField();
                } else {
                    application.showMsg("Đã xảy ra lỗi. Vui lòng thử lại!", Alert.AlertType.ERROR);
                }
            } else {
                studentManagement.update(studentId, student);
                application.showMsg("Sửa học viên thành công!", Alert.AlertType.INFORMATION);
                loadAllStudent();
                clearField();
                isEdit = false;
                studentId = -1;
            }
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        if (isEdit && studentId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn thực sự muốn xóa?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK){
                studentManagement.delete(studentId);
                application.showMsg("Xóa thành công!", Alert.AlertType.INFORMATION);
                loadAllStudent();
                clearField();
                studentId = -1;
                isEdit = false;
                btnDelete.setDisable(true);
            }
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        studentId = tableView.getSelectionModel().getSelectedIndex();
        if (studentId != -1) {
            name.setText(studentManagement.get(studentId).getName());
            address.setText(studentManagement.get(studentId).getAddress());
            phone.setText(studentManagement.get(studentId).getPhone());
            email.setText(studentManagement.get(studentId).getEmail());
            isEdit = true;
            btnDelete.setDisable(false);
        }
    }

    private void loadAllStudent() {
        tableView.getItems().clear();
        for (Student student: studentManagement.getAll()) {
            tableView.getItems().add(student);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validator = new Validator();
        studentManagement = new StudentIManagement();
        fileIOManagement = new FileIOManagement();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.getItems().addAll(studentManagement.getAll());
    }

    public void btnExport(ActionEvent actionEvent) {
        try {
            fileIOManagement.writeCSV(studentManagement.getAll());
            application.showMsg("Xuất file CSV thành công!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnImport(ActionEvent actionEvent) {
        List<Student> list = fileIOManagement.readCSV();
        if (list != null) {
            studentManagement.setStudents(list);
            loadAllStudent();
            application.showMsg("Nhập file CSV thành công!", Alert.AlertType.INFORMATION);
        } else {
            application.showMsg("Nhập file CSV thất bại!", Alert.AlertType.ERROR);
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        String q = searchField.getText().toLowerCase().trim();
        List<Student> students = new ArrayList<>();
        for (Student student: studentManagement.getAll()) {
            if (student.getName().toLowerCase().trim().contains(q)) {
                students.add(student);
            }
        }
        if (students.size() > 0) {
            application.showMsg("Tìm thấy " + students.size() + " kết quả cho " + searchField.getText(), Alert.AlertType.INFORMATION);
            tableView.getItems().clear();
            for (Student student: students) {
                tableView.getItems().add(student);
            }
        } else {
            application.showMsg("Không tìm thấy kết quả nào!", Alert.AlertType.INFORMATION);
        }
    }
}
