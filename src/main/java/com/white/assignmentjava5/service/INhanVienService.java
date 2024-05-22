package com.white.assignmentjava5.service;

import com.white.assignmentjava5.entity.NhanVien;

public interface INhanVienService extends IService<NhanVien,String> {
    NhanVien findByTenDangNhap(String tenDangNhap);
}
