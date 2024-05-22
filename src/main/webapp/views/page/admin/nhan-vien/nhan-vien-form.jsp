<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title>Thêm nhân Viên</title>
<body>
<div class="container-fluid px-4">
    <h1 class="mt-4">Action</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">Thêm Nhân Viên</li>
    </ol>
    <div class="card mb-4">
        <div class="card-body">
            <form method="post" action="<c:url value="${formLink}"/>">
                <div class="form-group mb-3">
                    <label for="tenNhanVien">Tên Nhân Viên</label>
                    <input type="text" class="form-control" id="tenNhanVien"
                           name="tenNhanVien"
                           aria-describedby="tenSanPham"
                           placeholder="Tên Nhân Viên" value="${nhanVien.tenNhanVien}">
                    <span style="color: red">${errors.get("tenNhanVien")}</span>
                </div>
                <div class="form-group mb-3">
                    <label for="tenDangNhap">Tên Đăng Nhập</label>
                    <input type="text" class="form-control" id="tenDangNhap"
                           name="tenDangNhap"
                           aria-describedby="tenSanPham"
                           placeholder="Tên Đăng Nhập" value="${nhanVien.tenDangNhap}">
                    <span style="color: red">${errors.get("tenDangNhap")}</span>
                </div>

                <div class="form-group mb-3">
                    <label for="tenSanPham">Mật khẩu</label>
                    <input type="password" class="form-control" id="tenSanPham"
                           name="matKhau"
                           aria-describedby="tenSanPham"
                           placeholder="Mật khẩu" value="${nhanVien.matKhau}">
                    <span style="color: red">${errors.get("matKhau")}</span>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai" value="true"
                           id="trangThaiKhaDung" ${nhanVien.trangThai?'checked':''}>
                    <label class="form-check-label" for="trangThaiKhaDung">
                        Khả dụng
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio"
                           name="trangThai" value="false"
                           id="trangThaiKhongKhaDung"
                    ${nhanVien.trangThai?'':'checked'}>
                    <label class="form-check-label" for="trangThaiKhongKhaDung">
                        Không khả dụng
                    </label>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Submit</button>
            </form>
        </div>
    </div>

</div>
</body>