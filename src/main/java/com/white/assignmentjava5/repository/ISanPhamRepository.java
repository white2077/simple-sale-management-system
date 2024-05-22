package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISanPhamRepository extends JpaRepository<SanPham, String> {
    Optional<SanPham> findByMaSanPham(String maSanPham);
    void deleteByMaSanPham(String maSanPham);
}
