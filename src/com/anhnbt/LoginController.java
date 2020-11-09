package com.anhnbt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Main application;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public LoginController() {
        System.out.println("Load Controller...");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }

    @FXML
    private void btnLogin(ActionEvent actionEvent) throws IOException {
        System.out.println("Login as " + application.getUser());
        if (application == null) {
            return;
        } else {
            if (username.getText().isEmpty()) {
                application.showMsg("Tên tài khoản không được để trống!", Alert.AlertType.ERROR);
            } else if (password.getText().isEmpty()) {
                application.showMsg("Mật khẩu không được để trống!", Alert.AlertType.ERROR);
            } else if (!application.userLogging(username.getText(), password.getText())) {
                application.showMsg("Tài khoản hoặc mật khẩu không đúng!", Alert.AlertType.ERROR);
            } else {
                application.showMsg("Đăng nhập thành công!", Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    private void btnReset(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        username.clear();
        password.clear();
    }

    public void setApplication(Main application) {
        this.application = application;
    }
}
