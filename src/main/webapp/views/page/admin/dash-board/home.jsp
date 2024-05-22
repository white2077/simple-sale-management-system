<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
    <div class="container-fluid px-4">
        <h1 class="mt-4">Dashboard</h1>
        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item active">Dashboard</li>
        </ol>
        <div class="row">
            <div class="col-xl-3 col-md-6">
                <div class="card bg-primary text-white mb-4">
                    <div class="card-body">Primary Card</div>
                    <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="#">View Details</a>
                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card bg-warning text-white mb-4">
                    <div class="card-body">Warning Card</div>
                    <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="#">View Details</a>
                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card bg-success text-white mb-4">
                    <div class="card-body">Success Card</div>
                    <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="#">View Details</a>
                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card bg-danger text-white mb-4">
                    <div class="card-body">Danger Card</div>
                    <div class="card-footer d-flex align-items-center justify-content-between">
                        <a class="small text-white stretched-link" href="#">View Details</a>
                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-6">
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-chart-area me-1"></i>
                        Area Chart Example
                    </div>
                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                </div>
            </div>
            <div class="col-xl-6">
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-chart-bar me-1"></i>
                        Bar Chart Example
                    </div>
                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                </div>
            </div>
        </div>
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table me-1"></i>
                Hoa Don
            </div>
            <div class="card-body">
                <table id="datatablesSimple">
                    <thead>
                    <tr>
                        <th>Ma Hoa Don</th>
                        <th>Khach Hang</th>
                        <th>Nhan Vien</th>
                        <th>Tong Tien</th>
                        <th>Ngay tao</th>
                        <th>Trang thai</th>
                        <th>Phuong thuc thanh toan</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Ma Hoa Don</th>
                        <th>Khach Hang</th>
                        <th>Nhan Vien</th>
                        <th>Tong Tien</th>
                        <th>Ngay tao</th>
                        <th>Trang thai</th>
                        <th>Phuong thuc thanh toan</th>
                    </tr>
                    </tfoot>
                    <tbody>
                   <c:forEach items="${hoaDons}" var="hoaDon" >
                        <tr>
                            <td>${hoaDon.id}</td>
                            <td>${hoaDon.khachHang.tenKhachHang}</td>
                            <td>${hoaDon.nhanVien.tenNhanVien}</td>
                            <td>${hoaDon.tongTien}</td>
                            <td>${hoaDon.ngayMuaHang}</td>
                            <td>${hoaDon.trangThai}</td>
                            <td>${hoaDon.phuongThucThanhToan}</td>
                        </tr>
                   </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>