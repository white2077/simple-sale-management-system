<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Quản lý Kích thước- Danh Sach</title>
<body>
<div class="container-fluid px-4">
<h1 class="mt-4">Quản lý kích thước</h1>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Toàn bộ kích thước</li>
</ol>
<a href="<c:url value="/admin/kich-thuoc/them-kich-thuoc"/>" class="btn btn-primary mb-4">Thêm Kích Thước</a>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-table me-1"></i>
        Toàn bộ kích thước
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Trạng thái</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Trạng thái</th>
                <th>Sửa</th>
                <th>Xóa</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${kichThuocs}" var="kichThuoc">
                <tr>
                    <td>${kichThuoc.maKichThuoc}</td>
                    <td>${kichThuoc.tenKichThuoc}</td>
                    <td>${kichThuoc.trangThai ? 'Khả dụng':'Không khả dụng'}</td>
                    <td><a class="btn btn-warning"
                           href="/admin/kich-thuoc/sua-kich-thuoc/${kichThuoc.maKichThuoc}">Sửa</a></td>
                    <td><a class="btn btn-danger">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>