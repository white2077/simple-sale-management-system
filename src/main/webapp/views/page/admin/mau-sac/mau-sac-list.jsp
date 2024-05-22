<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Quản lý sản phẩm - Danh Sach</title>
<body>
<div class="container-fluid px-4">
<h1 class="mt-4">Quản lý màu sắc</h1>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Toàn bộ màu sắc</li>
</ol>
<a href="<c:url value="/admin/mau-sac/them-mau-sac"/>" class="btn btn-primary mb-4">Thêm Màu Sắc</a>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-table me-1"></i>
        Toàn bộ màu sắc
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
            <c:forEach items="${mauSacs}" var="mauSac">
                <tr>
                    <td>${mauSac.maMauSac}</td>
                    <td>${mauSac.tenMauSac}</td>
                    <td>${mauSac.trangThai ? 'Khả dụng':'Không khả dụng'}</td>
                    <td><a class="btn btn-warning"
                           href="/admin/mau-sac/sua-mau-sac/${mauSac.maMauSac}">Sửa</a></td>
                    <td><a class="btn btn-danger">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>