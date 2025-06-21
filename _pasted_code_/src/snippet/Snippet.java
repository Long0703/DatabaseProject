package snippet;

public class Snippet {
	<!DOCTYPE html>
	<html lang="en" xmlns:th="http://www.thymeleaf.org">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Đăng nhập</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body class="bg-light">
	    <div class="container">
	        <div class="row justify-content-center mt-5">
	            <div class="col-md-6">
	                <div class="card shadow">
	                    <div class="card-body">
	                        <h2 class="card-title text-center mb-4">Đăng nhập</h2>
	
	                        <!-- Hiển thị thông báo lỗi nếu có -->
	                        <div th:if="${error != null}" class="alert alert-danger" role="alert">
	                            <span th:text="${error}"></span>
	                        </div>
	
	                        <form th:action="@{/user/login}" method="post">
	                            <div class="mb-3">
	                                <label for="username" class="form-label">Tên đăng nhập</label>
	                                <input type="text" class="form-control" id="username" name="username" required>
	                            </div>
	                            <div class="mb-3">
	                                <label for="password" class="form-label">Mật khẩu</label>
	                                <input type="password" class="form-control" id="password" name="password" required>
	                            </div>
	                            <div class="d-grid">
	                                <button type="submit" class="btn btn-primary">Đăng nhập</button>
	                            </div>
	                        </form>
	                        <div class="text-center mt-3">
	                            <p>Chưa có tài khoản? <a th:href="@{/user/register}">Đăng ký ngay</a></p>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	</body>
	</html>
}

