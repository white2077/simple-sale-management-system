<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Thống kê</div>
                <a class="nav-link" href="${pageContext.request.contextPath}/web/trang-chu">
                    <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-gauge-high" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="gauge-high" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zM288 96a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM256 416c35.3 0 64-28.7 64-64c0-17.4-6.9-33.1-18.1-44.6L366 161.7c5.3-12.1-.2-26.3-12.3-31.6s-26.3 .2-31.6 12.3L257.9 288c-.6 0-1.3 0-1.9 0c-35.3 0-64 28.7-64 64s28.7 64 64 64zM176 144a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM96 288a32 32 0 1 0 0-64 32 32 0 1 0 0 64zm352-32a32 32 0 1 0 -64 0 32 32 0 1 0 64 0z"></path></svg><!-- <i class="fas fa-tachometer-alt"></i> Font Awesome fontawesome.com --></div>
                    Dashboard
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/seller/ban-hang/trang-ban-hang">
                    <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-gauge-high" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="gauge-high" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zM288 96a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM256 416c35.3 0 64-28.7 64-64c0-17.4-6.9-33.1-18.1-44.6L366 161.7c5.3-12.1-.2-26.3-12.3-31.6s-26.3 .2-31.6 12.3L257.9 288c-.6 0-1.3 0-1.9 0c-35.3 0-64 28.7-64 64s28.7 64 64 64zM176 144a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM96 288a32 32 0 1 0 0-64 32 32 0 1 0 0 64zm352-32a32 32 0 1 0 -64 0 32 32 0 1 0 64 0z"></path></svg><!-- <i class="fas fa-tachometer-alt"></i> Font Awesome fontawesome.com --></div>
                    Bán Hàng
                </a>
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                    <div class="sb-sidenav-menu-heading">Quản lý cửa hàng</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-table-columns" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="table-columns" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 96C0 60.7 28.7 32 64 32H448c35.3 0 64 28.7 64 64V416c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V96zm64 64V416H224V160H64zm384 0H288V416H448V160z"></path></svg><!-- <i class="fas fa-columns"></i> Font Awesome fontawesome.com --></div>
                        Quản trị Sản Phẩm
                        <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" data-fa-i2svg=""><path fill="currentColor" d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                    </a>
                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href=<c:url value="/admin/san-pham/toan-bo-san-pham"/>>Sản Phẩm</a>
                            <a class="nav-link" href="<c:url value="/admin/kich-thuoc/toan-bo-kich-thuoc"/>">Kích Thước</a>
                            <a class="nav-link" href="<c:url value="/admin/mau-sac/toan-bo-mau-sac"/>">Màu Sắc</a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts1" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-table-columns" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="table-columns" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M0 96C0 60.7 28.7 32 64 32H448c35.3 0 64 28.7 64 64V416c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V96zm64 64V416H224V160H64zm384 0H288V416H448V160z"></path></svg><!-- <i class="fas fa-columns"></i> Font Awesome fontawesome.com --></div>
                        Quản trị nhân viên
                        <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" data-fa-i2svg=""><path fill="currentColor" d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                    </a>
                    <div class="collapse" id="collapseLayouts1" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="<c:url value="/admin/nhan-vien/danh-sach-nhan-vien"/>">Nhân viên</a>
                            <a class="nav-link" href="<c:url value="/admin/khach-hang/danh-sach-khach-hang"/>">Khách hàng</a>

                        </nav>
                    </div>
                </sec:authorize>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Đang đăng nhập với:</div>
            <sec:authorize access="isAuthenticated()">
                <c:set var="auth" value="${pageContext.request.userPrincipal}" />
                <c:out value="${auth.principal.tenNhanVien}"/>
            </sec:authorize>
        </div>
    </nav>
</div>