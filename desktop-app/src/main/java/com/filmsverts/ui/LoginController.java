package com.filmsverts.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.filmsverts.api.ApiClient;
import javafx.concurrent.Task;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private RadioButton userRadio;
    
    @FXML
    private RadioButton adminRadio;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Button loginButton;

    private ApiClient apiClient = new ApiClient();
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAdmin = adminRadio.isSelected();
        
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        loginButton.setDisable(true);
        messageLabel.setText("Đang đăng nhập...");

        Task<Boolean> loginTask = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                return apiClient.login(username, password, isAdmin);
            }
        };

        loginTask.setOnSucceeded(e -> {
            loginButton.setDisable(false);
            if (loginTask.getValue()) {
                messageLabel.setText("Đăng nhập thành công!");
                messageLabel.setStyle("-fx-text-fill: green;");
                openMainWindow(isAdmin);
            } else {
                messageLabel.setText("Đăng nhập thất bại! Kiểm tra lại thông tin.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        loginTask.setOnFailed(e -> {
            loginButton.setDisable(false);
            messageLabel.setText("Lỗi kết nối: " + loginTask.getException().getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
        });

        new Thread(loginTask).start();
    }
    
    private void openMainWindow(boolean isAdmin) {
        try {
            String fxmlFile = isAdmin ? "/fxml/AdminMain.fxml" : "/fxml/UserMain.fxml";
            String title = isAdmin ? "Filmsverts - Admin Panel" : "Filmsverts - User Panel";
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            
            // Close login window
            ((Stage) usernameField.getScene().getWindow()).close();
            
        } catch (Exception e) {
            messageLabel.setText("Lỗi mở ứng dụng: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Đăng ký tài khoản mới");
            stage.setScene(new Scene(root, 450, 500));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            messageLabel.setText("Lỗi mở form đăng ký: " + e.getMessage());
        }
    }
} 