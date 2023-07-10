<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Sing Up User </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        body {
            background-color: #f3f3f3;
        }

        .login-form {
            margin-top: 50px;
            padding: 25px;
            background-color: #ffffff;
            box-shadow: 0px 0px 5px #cccccc;
            border-radius: 5px;
        }
    </style>
</head>

<body class="container mt-3">
<h2 align="center" class="form-signin-heading">Đổi mật khẩu</h2>
<form class="login-form" id="registerForm" action="/update-pass/${US.id}" method="post">

    <div class="form-group">
        <label for="passHT">Mật khẩu hiện tại:</label>
        <input type="password" class="form-control" id="passHT" name="matKhauHT" required>
        <span style="color: red">${checkPass == 1?"Mat khau khong chinh xac!":""}</span>
    </div>
    <div class="form-group">
        <label for="password">Mật khẩu mới:</label>
        <input type="password" class="form-control" id="password" name="matKhau" minlength="6" required>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
        <input type="password" class="form-control" id="confirmPassword" minlength="6" required>
    </div>
    <br/>
    <center>
        <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
        <a href="/client/{id}" type="submit" class="btn btn-secondary">CLose</a>
    </center>
</form>
<script>
    // Lấy các trường và nút
    const form = document.getElementById('registerForm');
    const passwordht = document.getElementById('passHT');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const submitBtn = document.querySelector('button[type="submit"]');

    // Hàm validate form
    const validateForm = () => {
        if (password.value !== confirmPassword.value) {
            confirmPassword.setCustomValidity('Mật khẩu xác nhận không giống mật khẩu');
        } else {
            confirmPassword.setCustomValidity('');
        }
    }

    // Thêm sự kiện submit form
    form.addEventListener('submit', (e) => {
        validateForm();
        if (!form.checkValidity()) {
            e.preventDefault();
            e.stopPropagation();
        }
        form.classList.add('was-validated');
        alert("Success!");
    });

    // Thêm sự kiện khi người dùng nhập vào các trường
    passwordht.addEventListener('input', validateForm);
    password.addEventListener('input', validateForm);
    confirmPassword.addEventListener('input', validateForm);
</script>

<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>

</html>