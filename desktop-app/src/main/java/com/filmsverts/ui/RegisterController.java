package com.filmsverts.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.filmsverts.api.ApiClient;
import java.util.HashMap;
import java.util.Map;
import javafx.concurrent.Task;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField firstnameField;
    @FXML private TextField lastnameField;
    @FXML private TextField phonenumberField;
    @FXML private TextField dateofbirthField;
    @FXML private RadioButton userRadio;
    @FXML private RadioButton adminRadio;
    @FXML private Label messageLabel;
    @FXML private Button registerButton;

    private ApiClient apiClient = new ApiClient();

    @FXML
    private void handleRegister() {
        // Tắt nút đăng ký để tránh bấm nhiều lần
        registerButton.setDisable(true);
        messageLabel.setText("Đang xử lý...");

        // Tạo một Task để chạy tác vụ nền
        Task<Boolean> registerTask = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                boolean isAdmin = adminRadio.isSelected();
                Map<String, Object> payload = new HashMap<>();
                payload.put("isAdmin", isAdmin);
                payload.put("password", passwordField.getText());
                payload.put("email", emailField.getText());
                payload.put("firstname", firstnameField.getText());
                payload.put("lastname", lastnameField.getText());
                payload.put("phonenumber", phonenumberField.getText());
                payload.put("dateofbirth", dateofbirthField.getText());
                if (!isAdmin) {
                    payload.put("username", usernameField.getText());
                }
                return apiClient.register(payload);
            }
        };

        // Xử lý khi tác vụ thành công
        registerTask.setOnSucceeded(e -> {
            registerButton.setDisable(false);
            if (registerTask.getValue()) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Đăng ký thành công! Quay lại đăng nhập.");
                // Đóng form sau 1s
                new Thread(() -> {
                    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                    javafx.application.Platform.runLater(() -> ((Stage)registerButton.getScene().getWindow()).close());
                }).start();
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Đăng ký thất bại! Kiểm tra lại thông tin.");
            }
        });

        // Xử lý khi tác vụ thất bại
        registerTask.setOnFailed(e -> {
            registerButton.setDisable(false);
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Lỗi: " + registerTask.getException().getMessage());
        });

        // Chạy tác vụ trên một luồng mới
        new Thread(registerTask).start();
    }
} 