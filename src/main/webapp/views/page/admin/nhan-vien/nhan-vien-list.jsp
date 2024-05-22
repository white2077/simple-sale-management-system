<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Quản lý sản phẩm - Danh Sach</title>
<body>
<div class="container-fluid px-4">
<h1 class="mt-4">Quản lý nhân viên</h1>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Toàn bộ nhân viên</li>
</ol>
<a href="<c:url value="/admin/nhan-vien/them-nhan-vien"/>" class="btn btn-primary mb-4">Thêm nhân viên</a>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-table me-1"></i>
        Toàn bộ nhân viên
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Tên Đăng nhâp</th>
                <th>Trạng thái</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Tên Đăng nhâp</th>
                <th>Trạng thái</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${nhanViens}" var="nhanVien">
                <tr>
                    <td>${nhanVien.maNhanVien}</td>
                    <td>${nhanVien.tenDangNhap}</td>
                    <td>${nhanVien.tenNhanVien}</td>
                    <td>${nhanVien.trangThai ? 'Đang làm':'Đã nghỉ'}</td>
                    <td><a class="btn btn-warning"
                           href="/admin/nhan-vien/sua-nhan-vien/${nhanVien.maNhanVien}">Sửa</a></td>
                    <td><a class="btn btn-danger">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>