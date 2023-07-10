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
<h2 align="center" class="form-signin-heading">CẬP NHẬP TÀI KHOẢN</h2>
<form class="login-form" id="registerForm" action="/update-user/${US.id}" method="post">
    <div class="form-group">
        <label for="ma">Mã :</label>
        <input type="text" class="form-control" id="ma" name="ma" value="${US.ma}" required>
    </div>
    <div class="form-group">
        <label for="ten">Họ và tên:</label>
        <input type="text" class="form-control" id="ten" name="ten" value="${US.ten}" required>
    </div>
    <div class="form-group">
        <label>Giới tính:</label>
        <input type="radio" name="gioiTinh" value="Nam" ${US.gioiTinh == "Nam"?"checked":""}>Nam
        <input type="radio" name="gioiTinh" value="Nu"  ${US.gioiTinh == "Nu"?"checked":""}>Nữ
    </div>
    <div class="form-group">
        <label for="ngaySinh">Ngày Sinh:</label>
        <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="${US.ngaySinh}" required>
    </div>
    <div class="form-group">
        <label for="diaChi">Địa chỉ:</label>
        <input type="text" class="form-control" id="diaChi" name="diaChi" value="${US.diaChi}" required>
    </div>
    <div class="form-group">
        <label for="sdt">Số điện thoại:</label>
        <input type="text" class="form-control" id="sdt" name="sdt" value="${US.sdt}" required>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" name="email" value="${US.email}" required>
    </div>
    <br/>
    <center>
        <button type="submit" class="btn btn-primary">Cập nhập</button>
        <a href="/client" type="submit" class="btn btn-danger">Close</a>

    </center>
</form>
<script>
    // Lấy các trường và nút
    const form = document.getElementById('registerForm');
    const ma = document.getElementById('ma');
    const ten = document.getElementById('ten');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const ngaySinh = document.getElementById('ngaySinh');
    const diaChi = document.getElementById('diaChi');
    const sdt = document.getElementById('sdt');
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
        alert("Update Success");
    });

    // Thêm sự kiện khi người dùng nhập vào các trường
    ma.addEventListener('input', validateForm);
    ten.addEventListener('input', validateForm);
    diaChi.addEventListener('input', validateForm);
    sdt.addEventListener('input', validateForm);
    ngaySinh.addEventListener('input', validateForm);
    email.addEventListener('input', validateForm);
    password.addEventListener('input', validateForm);
    confirmPassword.addEventListener('input', validateForm);
</script>

<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>

</html>