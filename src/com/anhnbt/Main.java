package com.anhnbt;

import com.anhnbt.controller.StudentController;
import com.anhnbt.entities.User;
import com.anhnbt.services.Authenticator;
import com.anhnbt.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final double SCREEN_WIDTH = 960.0;
    public static final double SCREEN_HEIGHT = 480.0;

    private Stage stage;
    private Parent root;
    private User user;
    private Alert alert;

    @Override
    public void start(Stage primaryStage) throws Exception {
        alert = new Alert(Alert.AlertType.NONE);

        stage = primaryStage;
        stage.setMinWidth(SCREEN_WIDTH);
        stage.setMinHeight(SCREEN_HEIGHT);
        stage.setTitle("JavaFX Project Management");

        gotoLogin();
        primaryStage.show();
    }

    private void gotoLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/Login.fxml"));

        root = fxmlLoader.load();
        LoginController loginController = (LoginController) fxmlLoader.getController();
        loginController.setApplication(this);
        stage.setScene(new Scene(root));
    }

    private void gotoStudent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/Student.fxml"));

        root = fxmlLoader.load();
        StudentController studentController = (StudentController) fxmlLoader.getController();
        studentController.setApplication(this);
        stage.setScene(new Scene(root));
    }

    public void showMsg(String s, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setContentText(s);
        alert.show();
    }

    public User getUser() {
        return user;
    }

    public boolean userLogging(String username, String password) throws IOException {
        if (Authenticator.validate(username, password)) {
            user = User.getInstance(username);
            gotoStudent();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
