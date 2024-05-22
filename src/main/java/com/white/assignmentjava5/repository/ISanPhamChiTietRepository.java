package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.SanPhamChiTiet;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ISanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, String> {
    List<SanPhamChiTiet> findAllBySanPhamMaSanPham(String maSanPham);
    Optional<SanPhamChiTiet> findByMaSanPhamChiTiet(String maSanPham);
    void deleteBySanPhamMaSanPham(String maSanPham);

    List<SanPhamChiTiet> findAllByMaSanPhamChiTietIn(List<String> maSanPhamChiTiet);

    @Query("select s from SanPhamChiTiet s where s.sanPham.tenSanPham like %:keyword%  and s.trangThai=:status  or s.maSanPhamChiTiet like %:keyword%  and s.trangThai=:status")
    Page<SanPhamChiTiet> findAllBySanPhamTenSanPhamOrMaSanPhamLikeKeyword(@Param("keyword") String keyword,
                                                                          Pageable pageable,@Param("status") boolean status);
}
