<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title></title>
<body>
<div class="container-fluid px-4">
    <h1 class="mt-4">Action</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">Thêm sản phẩm</li>
    </ol>
    <div class="card">
        <div class="card-body">
            <form method="post" action="<c:url value="${formLink}"/>">
                <div class="form-group mb-3">
                    <label for="tenMauSac">Tên màu săc</label>
                    <input type="text" class="form-control" id="tenMauSac"
                           name="tenMauSac"
                           aria-describedby="tenMauSac"
                           placeholder="Tên sản phẩm" value="${mauSac.tenMauSac}">
                    <span class="text-danger">${errors.get("tenMauSac")}</span>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai" value="true"
                           id="trangThaiKhaDung" ${mauSac.trangThai?'checked':''}>
                    <label class="form-check-label" for="trangThaiKhaDung">
                        Khả dụng
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio"
                           name="trangThai" value="false"
                           id="trangThaiKhongKhaDung"
                    ${mauSac.trangThai?'':'checked'}>
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