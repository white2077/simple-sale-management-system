<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title></title>
<body>
<div class="container-fluid px-4">
    <h1 class="mt-4">Chi tiết sản phẩm</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">Chi tiết sản phẩm: ${sanPham.tenSanPham}</li>
    </ol>
    <div class="card mb-4">
        <div class="card-body">
            <form method="post" action="<c:url value="/admin/chi-tiet-san-pham/cap-nhat/${chiTietSanPham.maSanPhamChiTiet}"/>">
                <div class="form-group mb-3">
                    <label for="tenSanPham">Tên sản phẩm</label>
                    <input type="text" class="form-control" id="tenSanPham"
                           aria-describedby="tenSanPham" disabled value="${sanPham.tenSanPham}">
                </div>
                <div class="form-group mb-3">
                    <label for="soLuong">Số lượng</label>
                    <input type="number" class="form-control" id="soLuong" name="soLuong"
                           aria-describedby="soLuong"  value="${chiTietSanPham.soLuong}">
                    <span id="soLuongError" class="text-danger">${error.get("soLuong")}</span>
                </div>
                <div class="form-group mb-3">
                    <label for="donGia">Đơn giá</label>
                    <input type="number" class="form-control" id="donGia" name="donGia"
                           aria-describedby="soLuong"  value="${chiTietSanPham.donGia}">
                    <span  class="text-danger">${error.get("donGia")}</span>
                </div>

                <div class="form-group mb-3">
                    <label for="kichThuocId">Khích Thước</label>
                    <select class="form-select" aria-label="kichThuoc" id="kichThuocId" name="maKichThuoc">
                        <c:forEach items="${kichThuocs}" var="kichThuoc">
                            <option value="${kichThuoc.maKichThuoc}">${kichThuoc.tenKichThuoc}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label for="mauSacId">Màu sắc</label>
                    <select class="form-select" aria-label="kichThuoc" id="mauSacId" name="maMauSac">
                      <c:forEach items="${mauSacs}" var="mauSac">
                        <option value="${mauSac.maMauSac}">${mauSac.tenMauSac}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai" value="true"
                           id="trangThaiKhaDung" ${sanPham.trangThai?'checked':''}>
                    <label class="form-check-label" for="trangThaiKhaDung">
                        Khả dụng
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio"
                           name="trangThai" value="false"
                           id="trangThaiKhongKhaDung"
                    ${sanPham.trangThai?'':'checked'}>
                    <label class="form-check-label" for="trangThaiKhongKhaDung">
                        Không khả dụng
                    </label>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Submit</button>
            </form>
        </div>
    </div>
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
                    <th>Màu sắc</th>
                    <th>Khích Thước</th>
                    <th>Đơn giá</th>
                    <th>Số lượng</th>
                    <th>Trạng thái</th>
                    <th>Sửa</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>Mã</th>
                    <th>Màu sắc</th>
                    <th>Khích Thước</th>
                    <th>Đơn giá</th>
                    <th>Số lượng</th>
                    <th>Trạng thái</th>
                    <th>Sửa</th>
                    <th>Xóa</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${chiTietSanPhams}" var="chiTietSanPham">
                    <tr>
                        <td>${chiTietSanPham.maSanPhamChiTiet}</td>
                        <td>${chiTietSanPham.mauSac.tenMauSac}</td>
                        <td>${chiTietSanPham.kichThuoc.tenKichThuoc}</td>
                        <td>${chiTietSanPham.donGia}</td>
                        <td>${chiTietSanPham.soLuong}</td>
                        <td>${chiTietSanPham.trangThai ? 'Khả dụng':'Không khả dụng'}</td>
                        <td><a class="btn btn-warning"
                               href="/admin/chi-tiet-san-pham/cap-nhat-chi-tiet-san-pham/${chiTietSanPham.maSanPhamChiTiet}">Sửa</a></td>
                        <td><a class="btn btn-danger">Xóa</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>