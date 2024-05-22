<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Quản lý sản phẩm - Danh Sach</title>
<body>
<div class="container-fluid px-4">
<h1 class="mt-4">Quản lý màu sắc</h1>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Toàn bộ nhân viên</li>
</ol>
<a href="<c:url value="/admin/khach-hang/them-khach-hang"/>" class="btn btn-primary mb-4">Thêm Khách Hàng</a>
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
                <th>SDT</th>
                <th>Trạng thái</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${khachHangs}" var="khachHang">
                <tr>
                    <td>${khachHang.maKhachHang}</td>
                    <td>${khachHang.tenKhachHang}</td>
                    <td>${khachHang.soDienThoai}</td>
                    <td>${khachHang.trangThai ? 'Đang sống':'Đã chết'}</td>
                    <td><a class="btn btn-warning"
                           href="/admin/khach-hang/sua-khach-hang/${khachHang.maKhachHang}">Sửa</a></td>
                    <td><a class="btn btn-danger">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>