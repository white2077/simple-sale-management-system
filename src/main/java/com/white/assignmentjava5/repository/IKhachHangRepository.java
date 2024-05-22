package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IKhachHangRepository extends JpaRepository<KhachHang,String> {
    Optional<KhachHang> findByMaKhachHang(String ma);
    boolean existsBySoDienThoai(String soDienThoai);
}
