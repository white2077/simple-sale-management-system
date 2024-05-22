package com.white.assignmentjava5.service;



import com.white.assignmentjava5.entity.SanPhamChiTiet;

import java.util.List;

public interface IChiTietSanPhamService{
    public boolean checkQuantity(String maSanPhamChiTiet, int soLuong);
    public List<SanPhamChiTiet> serachByTenSanPham(String tenSanPham);
    List<SanPhamChiTiet> getAll();
    List<SanPhamChiTiet> getAllByMaSanPham(String maSanPham);
    SanPhamChiTiet getbyMaSanPham(String maSanPham);

    SanPhamChiTiet insert(SanPhamChiTiet sanPhamChiTiet);
    SanPhamChiTiet update(SanPhamChiTiet sanPhamChiTiet,String maSanPham);
    void delete(String maSanPham);

}
