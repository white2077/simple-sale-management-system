<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

</head>
<body class="sb-nav-fixed" ng-app="app">
    <%@include file="/views/layout/nav.jsp" %>
<div id="layoutSidenav">
    <%@include file="/views/layout/side-nav.jsp" %>
    <div id="layoutSidenav_content">
        <main>
            <sitemesh:write property="body"/>
        </main>
    <%@include file="/views/layout/footter.jsp"%>
    </div>
</div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.8.3/angular.min.js" integrity="sha512-KZmyTq3PLx9EZl0RHShHQuXtrvdJ+m35tuOiwlcZfs/rE7NZv29ygNA8SFCkMXTnYZQK2OX0Gm2qKGfvWEtRXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script>
        angular.module('app', []).controller('cartCtrl', function($scope,$http,$timeout) {
            $scope.cart = [];
            $scope.searchTerm = '';
            $scope.errorMsg = '';
            $scope.searchResults = [];
            $scope.toTal = 0;
            let timeoutPromise;
            fillAllCart()

            function fillAllCart() {
                $http.get('/seller/api/seller/cart/get-cart').then(function(response) {
                    $scope.cart = response.data;
                    $scope.toTal = 0;
                    $scope.cart.forEach(function(item) {
                        $scope.toTal += item.donGia;
                    });
                });
            }


            $scope.add = function(maSanPhamChiTiet,sanPham, soLuong,donGia) {
                $http.post('/seller/api/seller/cart/add', {
                    maSanPhamChiTiet: maSanPhamChiTiet,
                    sanPham: sanPham,
                    soLuong: 1,
                    donGia: donGia
                }).then(function(response) {
                    fillAllCart();
                }, function(error) {
                    $scope.errorMsg = error.data.error;
                    $timeout(function() {
                        $scope.errorMsg = '';
                    }, 5000);
                });
            };


            $scope.remove = function(maSanPhamChiTiet) {
                $http.get('/seller/api/seller/cart/remove/'+maSanPhamChiTiet).then(function(response) {
                    fillAllCart();
                }, function(error) {
                    console.error(error);
                });
            };


            $scope.search = function() {
                if (timeoutPromise) {
                    $timeout.cancel(timeoutPromise);  //cancel previous timeout
                }

                timeoutPromise = $timeout(function() {
                    if ($scope.searchTerm.length > 0) {
                        $http.get('/seller/api/seller/san-pham/search', {
                            params: { tenSanPham: $scope.searchTerm }
                        }).then(function(response) {
                            $scope.searchResults = response.data;
                        });
                    } else {
                        $scope.searchResults = [];
                    }
                }, 500);  //1 second delay
            };
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/chart-area-demo.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/datatables-simple-demo.js"></script>
</body>
</html>
