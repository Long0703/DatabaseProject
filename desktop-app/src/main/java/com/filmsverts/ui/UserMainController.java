package com.filmsverts.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.filmsverts.api.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Map;

public class UserMainController {
    
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
    }
    
    @FXML
    private void handleRefresh() {
        loadMovies();
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
            "description", "Siêu anh hùng Marvel"
        );
        
        Map<String, Object> movie2 = Map.of(
            "movieID", 2L,
            "title", "Spider-Man: No Way Home",
            "publishyear", 2021,
            "description", "Spider-Man đa vũ trụ"
        );
        
        sampleData.addAll(movie1, movie2);
        moviesTable.setItems(sampleData);
        statusLabel.setText("Đang hiển thị dữ liệu mẫu");
    }
} 