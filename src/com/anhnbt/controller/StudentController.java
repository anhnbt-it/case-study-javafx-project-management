package com.anhnbt.controller;

import com.anhnbt.Main;
import com.anhnbt.services.FileIOManagement;
import com.anhnbt.services.FormValidator;
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
    private Main application;
    private FileIOManagement fileIOManagement;
    private StudentIManagement studentManagement;
    private FormValidator formValidator;
    private boolean isEdit = false;
    private int studentId = -1;

    @FXML
    private TableColumn<Student, Integer> idCol;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formValidator = new FormValidator();
        studentManagement = new StudentIManagement();
        fileIOManagement = new FileIOManagement();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.getItems().addAll(studentManagement.getAll());
    }

    public void setApplication(Main application) {
        this.application = application;
    }

    public void btnSave(ActionEvent actionEvent) {
        if (!formValidator.name(name.getText())) {
            application.showMsg("Tên phải có độ dài từ 2 - 50 ký tự.", Alert.AlertType.ERROR);
        } else if (!formValidator.phone(phone.getText())) {
            application.showMsg("Số điện thoại sai định dạng!", Alert.AlertType.ERROR);
        } else if (!formValidator.email(email.getText())) {
            application.showMsg("Email sai định dạng!", Alert.AlertType.ERROR);
        } else {
            if (!isEdit) {
                Student student = new Student(Student.nextId, name.getText(), address.getText(), phone.getText(), email.getText());
                if (studentManagement.save(student)) {
                    application.showMsg("Thêm học viên thành công!", Alert.AlertType.INFORMATION);
                    tableView.getItems().add(student);
                    clearField();
                } else {
                    application.showMsg("Đã xảy ra lỗi. Vui lòng thử lại!", Alert.AlertType.ERROR);
                }
            } else {
                studentManagement.get(studentId).setName(name.getText());
                studentManagement.get(studentId).setAddress(address.getText());
                studentManagement.get(studentId).setPhone(phone.getText());
                studentManagement.get(studentId).setEmail(email.getText());
                application.showMsg("Sửa học viên thành công!", Alert.AlertType.INFORMATION);
                reloadTableView(studentManagement.getAll());
                clearField();
                isEdit = false;
                studentId = -1;
                btnDelete.setDisable(true);
            }
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        if (isEdit && studentId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn thực sự muốn xóa?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK) {
                studentManagement.delete(studentId);
                application.showMsg("Xóa thành công!", Alert.AlertType.INFORMATION);
                reloadTableView(studentManagement.getAll());
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

    private void reloadTableView(List<Student> students) {
        tableView.getItems().clear();
        for (Student student : students) {
            tableView.getItems().add(student);
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        String searchQuery = searchField.getText().toLowerCase().trim();
        List<Student> students = studentManagement.searchByName(searchQuery);
        if (students.size() > 0) {
            application.showMsg("Tìm thấy " + students.size() + " kết quả cho \"" + searchField.getText() + "\"", Alert.AlertType.INFORMATION);
            reloadTableView(students);
        } else {
            application.showMsg("Không tìm thấy kết quả nào!", Alert.AlertType.INFORMATION);
        }
    }

    public void btnExport(ActionEvent actionEvent) {
        try {
            fileIOManagement.writeCSVFile(studentManagement.getAll());
            application.showMsg("Xuất file CSV thành công!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnImport(ActionEvent actionEvent) {
        List<Student> list = fileIOManagement.readCSV();
        if (list != null) {
            studentManagement.setStudents(list);
            reloadTableView(studentManagement.getAll());
            application.showMsg("Nhập file CSV thành công!", Alert.AlertType.INFORMATION);
        } else {
            application.showMsg("Nhập file CSV thất bại!", Alert.AlertType.ERROR);
        }
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
}
