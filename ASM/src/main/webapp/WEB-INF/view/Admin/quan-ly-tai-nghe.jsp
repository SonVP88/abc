<%--
  Created by IntelliJ IDEA.
  User: DANG HIEU
  Date: 3/18/2023
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>QUAN LI TAI NGHE</title>
    <script>
        function validate() {
            var ma = document.getElementById("ma");
            var ten = document.getElementById("ten");

            var valid = true;
            if (ma.value.length <= 0 || ten.value.length <= 0) {
                alert("không được để trống!");
                valid = false;
            }
            return valid;
        };
    </script>
</head>

<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>
<br/><br/><br/><br/>
<div class="container">
    <h2 align="center">Quan Ly Tai Nghe</h2><br/>
    <br/><br/>
    <form action="/ASM/quan-ly-tai-nghe/add" method="post">
        <div>
                    <div class="form-group">
                        <label for="usr">Ten:</label>
                        <input required="true" type="text" class="form-control" id="usr" name="tenTaiNghe">
                    </div>
                    <div class="form-group">
                        <label for="soLuongTon">So luong ton:</label>
                        <input required="true" type="text" class="form-control" id="soLuongTon"
                               name="soLuongTon">
                    </div>
                    <div class="form-group">
                        <label for="ts">Tan So(Hz):</label>
                        <input type="text" class="form-control" id="ts" name="tanSo">
                    </div>
                    <div class="form-group">
                        <label for="ckn">Cong Ket Noi:</label>
                        <select class="form-select" aria-label="Default select example" name="congKetNoi"
                                id="ckn">
                            <option value="3.5">3.5</option>
                            <option value="Type-C">Type-C</option>
                            <option value="Bluetooth">Bluetooth</option>
                            <option value="Lightning">Lightning</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dg">Don Gia(VND):</label>
                        <input required="true" type="text" class="form-control" id="dg" name="donGia">
                    </div>
                    <div class="form-group">
                        <label for="ktmt">Kich Thuoc Mang Loa(mm):</label>
                        <input type="text" class="form-control" id="ktmt" name="kichThuocMangLoa">
                    </div>
                <center><button class="btn btn-success" data-bs-dismiss="modal" type="submit"
                        onclick="return confirmAction()">ADD
                </button></center>
        </div>
    </form>
    <br/>
    <form action="/ASM/quan-ly-tai-nghe/search">
        <div>
            <input type="search" class="form-control form-control" name="tenTaiNghe" placeholder="Ten Tai Nghe" value="${param.tenTaiNghe}"/>
            <input type="search" class="form-control form-control" name="giaMin" placeholder="Gia Min" value="${param.giaMin}"/>
            <input type="search" class="form-control form-control" name="giaMax" placeholder="Gia Max" value="${param.giaMax}">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
        <br/>
    </form>
    <c:if test="${page.isEmpty()}">
        <h2>Not found any records!!!</h2>
    </c:if><br/>
    <c:if test="${not page.isEmpty()}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Ten tai nghe</th>
                <th>So luong</th>
                <th>Tan so</th>
                <th>Cong ket Noi</th>
                <th>Kich thuoc mang loa</th>
                <th>Don gia</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.getContent()}" var="sp">
                <tr>
                    <td>${sp.taiNgheId}</td>
                    <td>${sp.tenTaiNghe}</td>
                    <td>${sp.soLuongTon}</td>
                    <td>${sp.tanSo} Hz</td>
                    <td>${sp.congKetNoi}</td>
                    <td>${sp.kichThuocMangLoa} mm</td>
                    <td>${sp.donGia} VND</td>
                    <td>
                        <a href="/ASM/quan-ly-tai-nghe/delete/${sp.taiNgheId}" class="btn btn-danger"
                           onclick="return confirmAction()">Remove</a>
                        <a href="/ASM/quan-ly-tai-nghe/view-update/${sp.taiNgheId}" class="btn btn-primary">Detail Or
                            Update</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form>
            <div>
                <ul class="pagination">
                    <li class="page-item">
                        <c:if test="${page.getNumber() + 1 > 1}">
                            <a class="page-link" href="?page=${page.getNumber()}&tenTaiNghe=${param.tenTaiNghe}">
                                Previous
                            </a>
                        </c:if>
                    </li>
                    <li class="page-item"><a class="page-link"> ${page.getNumber() + 1}</a></li>
                    <li class="page-item"><a class="page-link">/</a></li>
                    <li class="page-item"><a class="page-link"> ${page.getTotalPages()}</a></li>
                    <li class="page-item"><c:if test="${page.getNumber() + 1 lt page.getTotalPages()}">
                        <a class="page-link" href="?page=${page.getNumber() + 2}&tenTaiNghe=${param.tenTaiNghe}">
                            Next
                        </a>
                    </c:if>
                    </li>
                </ul>
            </div>
        </form>
    </c:if>
</div>

<%--modal add--%>
<div class="modal" id="ModalDetail">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Hoá đơn chi tiết</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body">

            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Add</button>
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<script>
    function confirmAction() {
        return confirm("Bạn có chắc chắn muốn thực hiện thao tác này không?");
    }
</script>

<jsp:include page="template/footer.jsp"></jsp:include>

</body>

</html>

