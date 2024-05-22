package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.NhanVien;
import com.white.assignmentjava5.security.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface INhanVienRepository extends JpaRepository<NhanVien,String> {
    Optional<NhanVien> findByTenDangNhap(String tenDangNhap);
    Optional<NhanVien> findByMaNhanVien(String ma);
    List<NhanVien> findAllByRoleLike(Role role);
    boolean existsByTenDangNhap(String tenDangNhap);
}
