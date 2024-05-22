<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title></title>
<body>
<div class="container-fluid px-4">
    <h1 class="mt-4">Trang Bán hàng</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">Toàn bộ sản phẩm</li>
    </ol>

    <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Thanh Toán
    </button>

    <!-- Modal -->
    <div class="modal fade mb-4" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Thanh toán</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/seller/thanh-toan/checkout" method="post">
                    <div class="modal-body">
                            <div class="form-check">
                                <h4>Tổng Tiền: </h4>
                                <input class="form-check-input" type="radio" name="phuongThucThanhToan" value="true"
                                       id="tienMat" checked>
                                <label class="form-check-label" for="tienMat">
                                    Tiền mặt
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio"
                                       name="phuongThucThanhToan" value="false"
                                       id="chuyenKhoan">
                                <label class="form-check-label" for="chuyenKhoan">
                                    Chuyển khoản VNPAY
                                </label>
                            </div>
                        <div class="form-check">
                            <label>
                                Khách hàng:
                            </label>
                            <br>
                            <select name="khachHang">
                                <c:forEach items="${khachHangs}" var="khachHang">
                                    <option value="${khachHang.maKhachHang}">${khachHang.tenKhachHang}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                       <div class="modal-footer">
                           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                           <button type="submit" class="btn btn-primary">Thanh Toán</button>
                       </div>
                </form>
            </div>
        </div>
    </div>

    <div class="container" ng-controller="cartCtrl">
        <div class="row">
            <div class="col-md-12 mb-4">
                <div>
                    <input type="text" ng-model="searchTerm" ng-change="search()" placeholder="Search for products">
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">Mã sản phẩm</th>
                            <th scope="col">sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th>Đơn giá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="product in searchResults" ng-click="add(product.maSanPhamChiTiet,product.sanPham,product.soLuong,product.donGia)">
                            <th scope="row">{{ product.maSanPhamChiTiet}}</th>
                            <td> {{ product.sanPham}}</td>
                            <td> {{ product.soLuong}} </td>
                            <td> {{ product.donGia}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <h6 style="color: red">{{errorMsg}}</h6>
            <div class="col-md-12">
                <h2>Giỏ hàng</h2>
                <h4>Tổng tiền: {{toTal}}</h4>
                <h4 style="color: red">${error}</h4>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Mã sản phẩm</th>
                        <th scope="col">sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th>Đơn giá</th>
                        <th>Xóa khỏi giỏ hàng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="x in cart" style="cursor:pointer">
                        <td>{{x.maSanPhamChiTiet}}</td>
                        <td>{{x.sanPham}}</td>
                        <td>{{x.soLuong}}</td>
                        <td>{{x.donGia}}</td>
                        <td><button ng-click="remove(x.maSanPhamChiTiet)" class="btn btn-danger">Xóa</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>