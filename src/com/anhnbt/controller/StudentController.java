package com.anhnbt.controller;

import com.anhnbt.Main;
import com.anhnbt.services.FileManager;
import com.anhnbt.services.Validator;
import com.anhnbt.entities.Student;
import com.anhnbt.services.StudentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    private FileManager fileManager;
    private Main application;
    private StudentDao studentDAO;
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
    private TextField query;

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
        if (application == null) {
            return;
        } else {
            if (!validator.name(name.getText())) {
                application.showMsg("Tên phải có độ dài từ 2 - 50 ký tự.", Alert.AlertType.ERROR);
            } else if (!validator.email(email.getText())) {
                application.showMsg("Email sai định dạng!", Alert.AlertType.ERROR);
            } else {
                Student student = new Student(name.getText(), address.getText(), phone.getText(), email.getText());
                if (!isEdit) {
                    if (studentDAO.save(student)) {
                        application.showMsg("Thêm học viên thành công!", Alert.AlertType.INFORMATION);
                        tableView.getItems().add(student);
                        clearField();
                    } else {
                        application.showMsg("Đã xảy ra lỗi. Vui lòng thử lại!", Alert.AlertType.ERROR);
                    }
                } else {

                    studentDAO.update(studentId, student);
                    application.showMsg("Sửa học viên thành công!", Alert.AlertType.INFORMATION);
                    loadAllStudent();
                    clearField();
                    isEdit = false;
                    studentId = -1;
                }
            }
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        if (isEdit && studentId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn thực sự muốn xóa?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK){
                studentDAO.delete(studentId);
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
            name.setText(studentDAO.get(studentId).getName());
            address.setText(studentDAO.get(studentId).getAddress());
            phone.setText(studentDAO.get(studentId).getPhone());
            email.setText(studentDAO.get(studentId).getEmail());
            isEdit = true;
            btnDelete.setDisable(false);
        }
    }

    private void loadAllStudent() {
        tableView.getItems().clear();
        for (Student student: studentDAO.getAll()) {
            tableView.getItems().add(student);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validator = new Validator();
        studentDAO = new StudentDao();
        fileManager = new FileManager();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.getItems().addAll(studentDAO.getAll());
    }

    public void writeData(ActionEvent actionEvent) {
        fileManager.writeToFile(studentDAO.getAll());
        application.showMsg("Ghi file thành công!", Alert.AlertType.INFORMATION);
    }

    public void readData(ActionEvent actionEvent) {
        List<Student> list = fileManager.readFromFile();
        if (list != null) {
            studentDAO.setStudents(list);
            loadAllStudent();
            application.showMsg("Ghi file thành công!", Alert.AlertType.INFORMATION);
        } else {
            application.showMsg("Đọc file thất bại!", Alert.AlertType.ERROR);
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        System.out.println(query.getText());

    }
}
