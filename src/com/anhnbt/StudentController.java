package com.anhnbt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    private Main application;
    private StudentDao studentDAO;
    private Validator validator;

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

                if (studentDAO.save(student)) {
                    application.showMsg("Thêm học viên thành công!", Alert.AlertType.INFORMATION);
                    tableView.getItems().add(student);
                    clearField();
                } else {
                    application.showMsg("Đã xảy ra lỗi. Vui lòng thử lại!", Alert.AlertType.ERROR);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validator = new Validator();
        studentDAO = new StudentDao();

        System.out.println("Size: " + studentDAO.getAll().size());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.getItems().addAll(studentDAO.getAll());
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        System.out.println(tableView.getSelectionModel().getSelectedIndex());
        int selectedItem = -1;
        selectedItem = tableView.getSelectionModel().getSelectedIndex();
        if (selectedItem != -1){
            name.setText(studentDAO.get(selectedItem).getName());
            address.setText(studentDAO.get(selectedItem).getAddress());
            phone.setText(studentDAO.get(selectedItem).getPhone());
            email.setText(studentDAO.get(selectedItem).getEmail());
        }
    }
}
