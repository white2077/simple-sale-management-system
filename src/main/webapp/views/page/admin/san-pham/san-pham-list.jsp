<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Quản lý sản phẩm - Danh Sach</title>
<body>
<div class="container-fluid px-4">
<h1 class="mt-4">Quản lý sản phẩm</h1>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Toàn bộ sản phẩm</li>
</ol>
<a href="<c:url value="/admin/san-pham/them-san-pham"/>" class="btn btn-primary mb-4">Thêm sản phẩm</a>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-table me-1"></i>
        Toàn bộ sản phẩm
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Trạng thái</th>
                <th>Thêm sản chi tiết sản phẩm</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Trạng thái</th>
                <th>Thêm sản chi tiết sản phẩm</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${sanPhams}" var="sanPham">
                <tr>
                    <td>${sanPham.maSanPham}</td>
                    <td>${sanPham.tenSanPham}</td>
                    <td>${sanPham.trangThai ? 'Khả dụng':'Không khả dụng'}</td>
                    <td><a class="btn btn-success"
                           href="/admin/chi-tiet-san-pham/toan-bo-chi-tiet-san-pham/${sanPham.maSanPham}">
                        Thêm chi tiết sản phẩm</a></td>
                    <td><a class="btn btn-warning"
                           href="/admin/san-pham/sua-san-pham/${sanPham.maSanPham}">Sửa</a></td>
                    <td><a class="btn btn-danger">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>