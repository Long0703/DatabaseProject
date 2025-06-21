# Filmsverts Desktop Application

Ứng dụng desktop JavaFX đơn giản để quản lý phim, kết nối với Backend Spring Boot.

## 🚀 Cách chạy ứng dụng

### 1. Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven 3.6+
- Backend Spring Boot đang chạy trên port 8080

### 2. Chạy Backend trước
```bash
cd ../filmsverts
./mvnw spring-boot:run
```

### 3. Chạy Desktop App
```bash
# Trong thư mục desktop-app
mvn clean javafx:run
```

Hoặc build và chạy JAR:
```bash
mvn clean package
java -jar target/filmsverts-desktop-1.0.0.jar
```

## 👤 Thông tin đăng nhập

### User Account:
- **Username:** `user`
- **Password:** `user123`

### Admin Account:
- **Username:** `1` (hoặc bất kỳ số nào)
- **Password:** `admin123`

## 🎯 Tính năng

### User Panel:
- Xem danh sách phim
- Làm mới dữ liệu
- Đăng xuất

### Admin Panel:
- Xem danh sách phim
- Thêm phim mới
- Xóa phim
- Làm mới dữ liệu
- Đăng xuất

## 📁 Cấu trúc project

```
desktop-app/
├── src/main/java/com/filmsverts/
│   ├── FilmsvertsDesktopApp.java    # Main class
│   ├── api/
│   │   └── ApiClient.java           # HTTP client cho API
│   └── ui/
│       ├── LoginController.java     # Controller đăng nhập
│       ├── UserMainController.java  # Controller User panel
│       └── AdminMainController.java # Controller Admin panel
├── src/main/resources/fxml/
│   ├── Login.fxml                   # Layout đăng nhập
│   ├── UserMain.fxml               # Layout User panel
│   └── AdminMain.fxml              # Layout Admin panel
└── pom.xml                         # Maven configuration
```

## 🔧 Cấu hình

### API Endpoint:
- Backend URL: `http://localhost:8080`
- Có thể thay đổi trong `ApiClient.java`

### Database:
- Cần có database PostgreSQL với tên `fimlsverts`
- Cập nhật thông tin kết nối trong Backend `application.properties`

## 🐛 Troubleshooting

### Lỗi kết nối Backend:
- Đảm bảo Backend Spring Boot đang chạy
- Kiểm tra port 8080 không bị chiếm
- Kiểm tra firewall

### Lỗi JavaFX:
- Đảm bảo Java 17+ đã cài đặt
- Kiểm tra JAVA_HOME environment variable

### Lỗi Maven:
- Chạy `mvn clean` trước khi build lại
- Kiểm tra internet connection để download dependencies 