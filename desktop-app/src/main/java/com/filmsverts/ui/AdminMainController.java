package com.filmsverts.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.filmsverts.api.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminMainController {
    
    @FXML
    private TableView<Map<String, Object>> moviesTable;
    
    @FXML
    private TableColumn<Map<String, Object>, String> idColumn;
    
    @FXML
    private TableColumn<Map<String, Object>, String> titleColumn;
    
    @FXML
    private TableColumn<Map<String, Object>, String> yearColumn;
    
    @FXML
    private TableColumn<Map<String, Object>, String> descriptionColumn;
    
    @FXML
    private TableColumn<Map<String, Object>, String> genreColumn;
    
    @FXML
    private Label statusLabel;
    
    private ApiClient apiClient = new ApiClient();
    private Gson gson = new Gson();
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadMovies();
    }
    
    private void setupTableColumns() {
        idColumn.setCellValueFactory(data -> {
            Object value = data.getValue().get("movieID");
            return new javafx.beans.property.SimpleStringProperty(value != null ? value.toString() : "");
        });
        
        titleColumn.setCellValueFactory(data -> {
            Object value = data.getValue().get("title");
            return new javafx.beans.property.SimpleStringProperty(value != null ? value.toString() : "");
        });
        
        yearColumn.setCellValueFactory(data -> {
            Object value = data.getValue().get("publishyear");
            return new javafx.beans.property.SimpleStringProperty(value != null ? value.toString() : "");
        });
        
        descriptionColumn.setCellValueFactory(data -> {
            Object value = data.getValue().get("description");
            return new javafx.beans.property.SimpleStringProperty(value != null ? value.toString() : "");
        });
        
        genreColumn.setCellValueFactory(data -> {
            Object value = data.getValue().get("genreID");
            return new javafx.beans.property.SimpleStringProperty(value != null ? value.toString() : "");
        });
    }
    
    @FXML
    private void handleRefresh() {
        loadMovies();
    }
    
    @FXML
    private void handleAddMovie() {
        showAddMovieDialog();
    }
    
    @FXML
    private void handleDeleteMovie() {
        Map<String, Object> selectedMovie = moviesTable.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            statusLabel.setText("Vui lòng chọn phim để xóa!");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc muốn xóa phim này?");
        alert.setContentText("Phim: " + selectedMovie.get("title"));
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Long movieId = Long.valueOf(selectedMovie.get("movieID").toString());
                boolean success = apiClient.deleteMovie(movieId);
                
                if (success) {
                    statusLabel.setText("Đã xóa phim thành công!");
                    loadMovies();
                } else {
                    statusLabel.setText("Lỗi khi xóa phim!");
                }
            }
        });
    }
    
    @FXML
    private void handleLogout() {
        try {
            // Open login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Filmsverts - Login");
            stage.setScene(new Scene(root, 400, 300));
            stage.setResizable(false);
            stage.show();
            
            // Close current window
            ((Stage) moviesTable.getScene().getWindow()).close();
            
        } catch (Exception e) {
            statusLabel.setText("Lỗi: " + e.getMessage());
        }
    }
    
    private void showAddMovieDialog() {
        Dialog<Map<String, Object>> dialog = new Dialog<>();
        dialog.setTitle("Thêm phim mới");
        dialog.setHeaderText("Nhập thông tin phim");
        
        // Set the button types
        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        
        // Create the custom content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField titleField = new TextField();
        titleField.setPromptText("Tên phim");
        TextField yearField = new TextField();
        yearField.setPromptText("Năm");
        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Mô tả");
        descriptionField.setPrefRowCount(3);
        
        grid.add(new Label("Tên phim:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Năm:"), 0, 1);
        grid.add(yearField, 1, 1);
        grid.add(new Label("Mô tả:"), 0, 2);
        grid.add(descriptionField, 1, 2);
        
        dialog.getDialogPane().setContent(grid);
        
        // Request focus on the title field by default
        titleField.requestFocus();
        
        // Convert the result to a map when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return Map.of(
                    "title", titleField.getText(),
                    "publishyear", Integer.parseInt(yearField.getText()),
                    "description", descriptionField.getText()
                );
            }
            return null;
        });
        
        Optional<Map<String, Object>> result = dialog.showAndWait();
        
        result.ifPresent(movieData -> {
            String jsonData = gson.toJson(movieData);
            boolean success = apiClient.addMovie(jsonData);
            
            if (success) {
                statusLabel.setText("Đã thêm phim thành công!");
                loadMovies();
            } else {
                statusLabel.setText("Lỗi khi thêm phim!");
            }
        });
    }
    
    private void loadMovies() {
        try {
            statusLabel.setText("Đang tải dữ liệu...");
            
            String moviesJson = apiClient.getMovies();
            List<Map<String, Object>> movies = gson.fromJson(moviesJson, new TypeToken<List<Map<String, Object>>>(){}.getType());
            
            ObservableList<Map<String, Object>> observableMovies = FXCollections.observableArrayList(movies);
            moviesTable.setItems(observableMovies);
            
            statusLabel.setText("Đã tải " + movies.size() + " phim");
            
        } catch (Exception e) {
            statusLabel.setText("Lỗi tải dữ liệu: " + e.getMessage());
            // Load sample data for demo
            loadSampleData();
        }
    }
    
    private void loadSampleData() {
        ObservableList<Map<String, Object>> sampleData = FXCollections.observableArrayList();
        
        Map<String, Object> movie1 = Map.of(
            "movieID", 1L,
            "title", "Avengers: Endgame",
            "publishyear", 2019,
            "description", "Siêu anh hùng Marvel",
            "genreID", 1L
        );
        
        Map<String, Object> movie2 = Map.of(
            "movieID", 2L,
            "title", "Spider-Man: No Way Home",
            "publishyear", 2021,
            "description", "Spider-Man đa vũ trụ",
            "genreID", 1L
        );
        
        sampleData.addAll(movie1, movie2);
        moviesTable.setItems(sampleData);
        statusLabel.setText("Đang hiển thị dữ liệu mẫu");
    }
} 